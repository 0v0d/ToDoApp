package com.example.todoapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.R
import com.example.todoapp.viewmodel.AddTaskFormViewModel

@Composable
fun AddTaskFromScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: AddTaskFormViewModel = hiltViewModel(),
    navigateToTodoListScreen: () -> Unit,
) {
    TaskForm(
        modifier = modifier,
        onBackClick = { onBackClick() },
        onSaveClick = { newTask ->
            viewModel.saveTask(newTask)
            navigateToTodoListScreen()
        },
        appBarTitleID = R.string.nav_label_add_task_title,
    )
}