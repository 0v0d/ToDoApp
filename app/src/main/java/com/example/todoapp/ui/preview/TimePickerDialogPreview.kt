package com.example.todoapp.ui.preview

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
import com.example.todoapp.ui.common.TimePickerDialog
import java.util.Calendar

@Preview
@Composable
fun TimePickerDialogPreview() {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    TimePickerDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = {}) {
                Text(stringResource(id = R.string.common_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = {}) {
                Text(stringResource(id = R.string.common_cancel))
            }
        }
    ) {
        TimePicker(
            state = timePickerState,
        )
    }
}