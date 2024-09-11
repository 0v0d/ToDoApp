package com.example.todoapp.ui.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.todoapp.R
import com.example.todoapp.ui.common.TimePickerDialog
import java.util.Calendar
import java.util.Date

@Composable
fun TaskDatePicker(
    initialDate: Date?,
    onDateSelect: (Date) -> Unit,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate?.time ?: System.currentTimeMillis()
    )
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let {
                    onDateSelect(Date(it))
                }
            }) {
                Text(stringResource(id = R.string.common_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.common_cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun TaskTimePicker(
    initialDate: Date?,
    onTimeSelect: (Date) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val calendar = Calendar.getInstance().apply {
        initialDate?.let { time = it }
    }
    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        is24Hour = true
    )
    TimePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                calendar.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                calendar.set(Calendar.MINUTE, timePickerState.minute)
                onTimeSelect(calendar.time)
            }) {
                Text(stringResource(id = R.string.common_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.common_cancel))
            }
        }
    ) {
        TimePicker(state = timePickerState)
    }
}
