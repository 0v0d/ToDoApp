package com.example.todoapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.navigation.NavigationGraph

@Composable
fun TodoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: TodoAppScreens.TaskList.name

    val currentScreen = when {
        currentRoute.startsWith(TodoAppScreens.EditTask.name) -> TodoAppScreens.EditTask
        currentRoute == TodoAppScreens.AddTask.name -> TodoAppScreens.AddTask
        else -> TodoAppScreens.TaskList
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TodoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )
    }
}

@Composable
fun TodoAppBar(
    currentScreen: TodoAppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        }
    )
}

enum class TodoAppScreens(@StringRes val title: Int) {
    TaskList(title = R.string.nav_label_todo_list_title),
    AddTask(title = R.string.nav_label_add_task_title),
    EditTask(title = R.string.nav_label_edit_task_title)
}
