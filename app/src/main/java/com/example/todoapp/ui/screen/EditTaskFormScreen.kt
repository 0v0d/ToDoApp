package com.example.todoapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.data.repository.model.TaskDomain
import com.example.todoapp.ui.component.TaskForm
import com.example.todoapp.utility.toEntity
import com.example.todoapp.viewmodel.EditTaskFormViewModel

@Composable
fun EditTaskFormScreen(
    task: TaskDomain,
    modifier: Modifier = Modifier,
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
