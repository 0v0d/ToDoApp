package com.example.todoapp.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.viewmodel.AddTaskFormViewModel

@Composable
fun AddTaskFromScreen(
    modifier: Modifier = Modifier,
    viewModel: AddTaskFormViewModel = hiltViewModel(),
    navigateToTodoListScreen: () -> Unit,
) {
    TaskForm(
        modifier = modifier.fillMaxWidth(),
        onSaveClick = { newTask ->
            viewModel.saveTask(newTask)
            navigateToTodoListScreen()
        },
    )
}