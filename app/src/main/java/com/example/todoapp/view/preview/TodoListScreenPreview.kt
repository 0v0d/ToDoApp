package com.example.todoapp.view.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.model.TaskDomain
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.view.TodoListContent

val taskList = listOf(
    TaskDomain(
        id = "1",
        title = "Task 1",
        description = "Description 1",
        completed = false,
        dueDate = "2025-01-01 00:00:00",
        position = 0
    ),
    TaskDomain(
        id = "2",
        title = "Task 2",
        description = "Description 2",
        completed = true,
        dueDate = "",
        position = 1
    ),
)

@Preview(showBackground = true)
@Composable
fun TodoListContentPreview() {
    AppTheme {
        TodoListContent(
            tasks = taskList,
            isLoading = false,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun TodoListContentPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TodoListContent(
            tasks = taskList,
            isLoading = false,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListContentNoItemPreview() {
    AppTheme {
        TodoListContent(
            tasks = emptyList(),
            isLoading = false,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun TodoListContentPreviewNoItemDarkMode() {
    AppTheme(darkTheme = true) {
        TodoListContent(
            tasks = emptyList(),
            isLoading = false,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListContentLoadingPreview() {
    AppTheme {
        TodoListContent(
            tasks = emptyList(),
            isLoading = true,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun TodoListContentPreviewLoadingDarkMode() {
    AppTheme(darkTheme = true) {
        TodoListContent(
            tasks = emptyList(),
            isLoading = true,
            onAddTask = {},
            onEditMode = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}