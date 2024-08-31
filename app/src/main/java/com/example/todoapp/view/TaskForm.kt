package com.example.todoapp.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.model.Task
import com.example.todoapp.theme.AppTheme
import com.example.todoapp.utility.DateUtility

@Composable
fun TaskForm(
    task: Task? = null,
    onSaveClick: (Task) -> Unit,
    onBackClick: () -> Unit,
    @StringRes appBarTitleID: Int,
    modifier: Modifier
) {
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var completed by remember { mutableStateOf(task?.completed ?: false) }
    var dueDate by remember { mutableStateOf(task?.dueDate) }

    var titleError by remember { mutableStateOf<String?>(null) }

    val (showDatePicker, setShowDatePicker) = remember { mutableStateOf(false) }
    val (showTimePicker, setShowTimePicker) = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBarTemplate(
                titleID = appBarTitleID,
                onBackClick = { onBackClick() },
                modifier = modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    titleError = null
                },
                label = { Text(stringResource(R.string.task_form_task_title)) },
                isError = titleError != null,
                supportingText = {
                    if (titleError != null) {
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

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.task_form_task_description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(bottom = 16.dp)
            )

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
                    text = dueDate?.let { DateUtility().getFormattedDate(it) }
                        ?: stringResource(R.string.task_form_task_undecided),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { setShowDatePicker(true) },
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(stringResource(R.string.task_form_task_date_select_button))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = completed,
                    onCheckedChange = { completed = it }
                )
                Text(stringResource(R.string.task_form_task_completed))
            }

            Button(
                onClick = {
                    val newTask = Task(
                        id = task?.id ?: "",
                        title = title,
                        description = description,
                        completed = completed,
                        dueDate = dueDate
                    )
                    onSaveClick(newTask)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.task_form_task_save))
            }
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { setShowDatePicker(false) },
                onDateSelected = { selectedDate ->
                    dueDate = selectedDate
                    setShowDatePicker(false)
                    setShowTimePicker(true)
                }
            )
        }

        if (showTimePicker) {
            TimePickerDialog(
                onDismissRequest = { setShowTimePicker(false) },
                onDateSelected = { selectedDate ->
                    dueDate = selectedDate
                    setShowTimePicker(false)
                }
            )
        }
    }
}

@Composable
@Preview
fun TaskFormPreview() {
    MaterialTheme {
        TaskForm(
            onSaveClick = {},
            onBackClick = {},
            appBarTitleID = R.string.nav_label_add_task_title,
            modifier = Modifier
        )
    }
}

@Composable
@Preview
fun TaskFormPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        TaskForm(
            onSaveClick = {},
            onBackClick = {},
            appBarTitleID = R.string.nav_label_add_task_title,
            modifier = Modifier
        )
    }
}