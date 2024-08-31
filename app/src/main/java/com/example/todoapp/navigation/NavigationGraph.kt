package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.model.TaskDomain
import com.example.todoapp.view.AddTaskFromScreen
import com.example.todoapp.view.EditTaskFormScreen
import com.example.todoapp.view.TodoListScreen
import com.google.gson.Gson

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.TODO_LIST_SCREEN.name,
    ) {
        addTodoListScreen(navController)
        addEditTaskScreen(navController)
        addAddTaskScreen(navController)
    }
}

private fun NavGraphBuilder.addTodoListScreen(navController: NavHostController) {
    composable(NavigationGraph.TODO_LIST_SCREEN.name) {
        TodoListScreen(
            onAddTask = {
                navController.navigateSingleTopTo(NavigationGraph.ADD_TASK_SCREEN.name)
            },
            onTaskClick = { taskDomain ->
                val taskJson = Gson().toJson(taskDomain)
                navController.navigateSingleTopTo(
                    "${NavigationGraph.EDIT_TASK_SCREEN.name}/$taskJson"
                )
            }
        )
    }
}

private fun NavGraphBuilder.addEditTaskScreen(navController: NavHostController) {
    composable(
        route = "${NavigationGraph.EDIT_TASK_SCREEN.name}/{taskJson}",
        arguments = listOf(navArgument("taskJson") { type = NavType.StringType })
    ) { backStackEntry ->
        val taskJson = backStackEntry.arguments?.getString("taskJson")
        val task = Gson().fromJson(taskJson, TaskDomain::class.java)

        EditTaskFormScreen(
            task = task,
            onBackClick = { navController.popBackStack() },
            navigateToTodoListScreen = {
                navController.navigateSingleTopTo(NavigationGraph.TODO_LIST_SCREEN.name)
            }
        )
    }
}

private fun NavGraphBuilder.addAddTaskScreen(navController: NavHostController) {
    composable(NavigationGraph.ADD_TASK_SCREEN.name) {
        AddTaskFromScreen(
            onBackClick = {
                navController.popBackStack()
            },
            navigateToTodoListScreen = {
                navController.navigateSingleTopTo(NavigationGraph.TODO_LIST_SCREEN.name)
            },
        )
    }
}


private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }


private enum class NavigationGraph {
    TODO_LIST_SCREEN,
    ADD_TASK_SCREEN,
    EDIT_TASK_SCREEN,
}
