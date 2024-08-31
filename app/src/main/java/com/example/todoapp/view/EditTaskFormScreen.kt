package com.example.todoapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.R
import com.example.todoapp.model.TaskDomain
import com.example.todoapp.model.toEntity
import com.example.todoapp.viewmodel.EditTaskFormViewModel

@Composable
fun EditTaskFormScreen(
    modifier: Modifier = Modifier,
    task: TaskDomain,
    viewModel: EditTaskFormViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToTodoListScreen: () -> Unit
) {

    TaskForm(
        modifier = modifier,
        onBackClick = { onBackClick() },
        onSaveClick = { newTask ->
            viewModel.updateTask(newTask)
            navigateToTodoListScreen()
        },
        appBarTitleID = R.string.nav_label_edit_task_title,
        task = task.toEntity()
    )
}