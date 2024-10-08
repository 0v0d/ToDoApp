package com.example.todoapp.ui.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.data.repository.model.Task
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.ui.component.TaskForm
import java.util.Date

private val task = Task(
    id = "1",
    title = "Task 1",
    description = "Description 1",
    completed = true,
    dueDate = Date(),
    position = 0
)

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun EditTaskFormPreview() {
    AppTheme {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize(),
            task = task
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun EditTaskFormPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize(),
            task = task
        )
    }
}
