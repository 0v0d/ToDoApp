package com.example.todoapp.ui.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.data.repository.model.TaskDomain
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.ui.screen.TodoListContent
import kotlinx.collections.immutable.toImmutableList

private val taskList = listOf(
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
).toImmutableList()

private val emptyList = emptyList<TaskDomain>().toImmutableList()

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun TodoListContentPreview() {
    AppTheme {
        TodoListContent(
            tasks = taskList,
            isLoading = false,
            onAddTask = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun TodoListContentPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TodoListContent(
            tasks = taskList,
            isLoading = false,
            onAddTask = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun TodoListContentNoItemPreview() {
    AppTheme {
        TodoListContent(
            tasks = emptyList,
            isLoading = false,
            onAddTask = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun TodoListContentPreviewNoItemDarkMode() {
    AppTheme(darkTheme = true) {
        TodoListContent(
            tasks = emptyList,
            isLoading = false,
            onAddTask = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun TodoListContentLoadingPreview() {
    AppTheme {
        TodoListContent(
            tasks = emptyList,
            isLoading = true,
            onAddTask = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun TodoListContentPreviewLoadingDarkMode() {
    AppTheme(darkTheme = true) {
        TodoListContent(
            tasks = emptyList,
            isLoading = true,
            onAddTask = {},
            onTaskClick = {},
            onDismiss = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
