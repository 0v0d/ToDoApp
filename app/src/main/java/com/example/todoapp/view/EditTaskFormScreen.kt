package com.example.todoapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.model.TaskDomain
import com.example.todoapp.model.toEntity
import com.example.todoapp.viewmodel.EditTaskFormViewModel

@Composable
fun EditTaskFormScreen(
    modifier: Modifier = Modifier,
    task: TaskDomain,
    viewModel: EditTaskFormViewModel = hiltViewModel(),
    navigateToTodoListScreen: () -> Unit
) {

    TaskForm(
        modifier = modifier,
        onSaveClick = { newTask ->
            viewModel.updateTask(newTask)
            navigateToTodoListScreen()
        },
        task = task.toEntity()
    )
}