package com.example.todoapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.utility.DateUtility
import java.util.Date

@Composable
fun TaskTitleInput(title: String, onTitleChange: (String) -> Unit, titleError: Boolean) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text(stringResource(R.string.task_form_task_title)) },
        supportingText = {
            if (titleError) {
                Text(
                    text = "Title is required",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun TaskDescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text(stringResource(R.string.task_form_task_description)) },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
fun TaskDueDateSelector(dueDate: Date?, dateUtility: DateUtility, onDatePickerShow: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.task_form_task_due_date),
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = dueDate?.let { dateUtility.getFormattedDate(it) }
                ?: stringResource(R.string.task_form_task_undecided),
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = onDatePickerShow,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(stringResource(R.string.task_form_task_date_select_button))
        }
    }
}

@Composable
fun TaskCompletionCheckbox(completed: Boolean, onCompletedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = completed,
            onCheckedChange = onCompletedChange
        )
        Text(stringResource(R.string.task_form_task_completed))
    }
}

@Composable
fun SaveTaskButton(onSaveClick: () -> Unit) {
    Button(
        onClick = onSaveClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.task_form_task_save))
    }
}