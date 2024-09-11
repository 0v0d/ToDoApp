package com.example.todoapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.repository.model.Task
import com.example.todoapp.utility.DateUtility

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    task: Task? = null,
    onSaveClick: (Task) -> Unit,
) {
    var taskFormState by remember { mutableStateOf(task?.toTaskState() ?: TaskFormState()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val dateUtility = remember { DateUtility() }

    Column(modifier = modifier.padding(16.dp)) {
        TaskTitleInput(
            title = taskFormState.title,
            onTitleChange = { taskFormState = taskFormState.copy(title = it, titleError = false) },
            titleError = taskFormState.titleError
        )

        TaskDescriptionInput(
            description = taskFormState.description,
            onDescriptionChange = { taskFormState = taskFormState.copy(description = it) }
        )

        TaskDueDateSelector(
            dueDate = taskFormState.dueDate,
            dateUtility = dateUtility,
            onDatePickerShow = { showDatePicker = true }
        )

        TaskCompletionCheckbox(
            completed = taskFormState.completed,
            onCompletedChange = { taskFormState = taskFormState.copy(completed = it) }
        )

        SaveTaskButton(
            onSaveClick = {
                if (taskFormState.title.isNotEmpty()) {
                    onSaveClick(taskFormState.toTask(task?.id, task?.position))
                } else {
                    taskFormState = taskFormState.copy(titleError = true)
                }
            }
        )
    }

    if (showDatePicker) {
        TaskDatePicker(
            initialDate = taskFormState.dueDate,
            onDateSelect = { selectedDate ->
                taskFormState = taskFormState.copy(dueDate = selectedDate)
                showDatePicker = false
                showTimePicker = true
            },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        TaskTimePicker(
            initialDate = taskFormState.dueDate,
            onTimeSelect = { selectedTime ->
                taskFormState = taskFormState.copy(dueDate = selectedTime)
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}
