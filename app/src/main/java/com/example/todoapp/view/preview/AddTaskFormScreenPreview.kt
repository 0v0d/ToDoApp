package com.example.todoapp.view.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.view.TaskForm

@Preview(showBackground = true)
@Composable
fun AddTaskFormPreview() {
    AppTheme {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun AddTaskFormPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TaskForm(
            onSaveClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

