package com.example.todoapp.view.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.model.Task
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.view.TaskForm
import java.util.Date

val task = Task(
    id = "1",
    title = "Task 1",
    description = "Description 1",
    completed = true,
    dueDate = Date(),
    position = 0
)

@Preview(showBackground = true)
@Composable
fun EditTaskFormPreview() {
    AppTheme {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize(),
            task = task
        )
    }
}

@Preview
@Composable
fun EditTaskFormPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize(),
            task = task
        )
    }
}