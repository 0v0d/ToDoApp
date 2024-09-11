package com.example.todoapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.data.repository.model.TaskDomain
import com.example.todoapp.ui.TodoAppScreens
import com.example.todoapp.ui.screen.AddTaskFromScreen
import com.example.todoapp.ui.screen.EditTaskFormScreen
import com.example.todoapp.ui.screen.TodoListScreen
import com.google.gson.Gson

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TodoAppScreens.TaskList.name,
        modifier = modifier
    ) {
        composable(TodoAppScreens.TaskList.name) {
            TodoListScreen(
                onAddTask = {
                    navController.navigateSingleTopTo(TodoAppScreens.AddTask.name)
                },
                onTaskClick = { taskDomain ->
                    val taskJson = Uri.encode(Gson().toJson(taskDomain))
                    navController.navigateSingleTopTo("${TodoAppScreens.EditTask.name}/$taskJson")
                },
            )
        }
        composable(
            route = "${TodoAppScreens.EditTask.name}/{taskJson}",
            arguments = listOf(navArgument("taskJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskJson = backStackEntry.arguments?.getString("taskJson")
            val task = Gson().fromJson(taskJson, TaskDomain::class.java)

            EditTaskFormScreen(
                task = task,
                navigateToTodoListScreen = {
                    navController.popBackStack(TodoAppScreens.TaskList.name, inclusive = false)
                },
            )
        }
        composable(route = TodoAppScreens.AddTask.name) {
            AddTaskFromScreen(
                navigateToTodoListScreen = {
                    navController.popBackStack(TodoAppScreens.TaskList.name, inclusive = false)
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
