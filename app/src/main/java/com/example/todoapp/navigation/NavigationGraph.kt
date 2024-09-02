package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.model.TaskDomain
import com.example.todoapp.view.AddTaskFromScreen
import com.example.todoapp.view.EditTaskFormScreen
import com.example.todoapp.view.TodoAppScreen
import com.example.todoapp.view.TodoListScreen
import com.google.gson.Gson

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TodoAppScreen.TaskList.name,
        modifier = modifier
    ) {
        composable(TodoAppScreen.TaskList.name) {
            TodoListScreen(
                onAddTask = {
                    navController.navigateSingleTopTo(TodoAppScreen.AddTask.name)
                },
                onTaskClick = { taskDomain ->
                    val taskJson = Gson().toJson(taskDomain)
                    navController.navigateSingleTopTo(
                        "${TodoAppScreen.EditTask.name}/$taskJson"
                    )
                },
            )
        }
        composable(
            route = "${TodoAppScreen.EditTask.name}/{taskJson}",
            arguments = listOf(navArgument("taskJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskJson = backStackEntry.arguments?.getString("taskJson")
            val task = Gson().fromJson(taskJson, TaskDomain::class.java)

            EditTaskFormScreen(
                task = task,
                navigateToTodoListScreen = {
                    navController.popBackStack(TodoAppScreen.TaskList.name, inclusive = false)
                },
            )
        }
        composable(route = TodoAppScreen.AddTask.name) {
            AddTaskFromScreen(
                navigateToTodoListScreen = {
                    navController.popBackStack(TodoAppScreen.TaskList.name, inclusive = false)
                },
            )
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }

