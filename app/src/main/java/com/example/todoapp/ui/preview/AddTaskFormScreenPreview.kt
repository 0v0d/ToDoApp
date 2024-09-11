package com.example.todoapp.ui.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.ui.component.TaskForm

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun AddTaskFormPreview() {
    AppTheme {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun AddTaskFormPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
