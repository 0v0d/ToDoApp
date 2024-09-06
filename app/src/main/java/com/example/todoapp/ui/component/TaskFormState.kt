package com.example.todoapp.ui.component

import com.example.todoapp.data.repository.model.Task
import java.util.Date

data class TaskFormState(
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false,
    val dueDate: Date? = null,
    val titleError: Boolean = false
)

fun Task.toTaskState() = TaskFormState(
    title = title,
    description = description,
    completed = completed,
    dueDate = dueDate
)

fun TaskFormState.toTask(id: String?, position: Int?) = Task(
    id = id ?: "",
    title = title,
    description = description,
    completed = completed,
    dueDate = dueDate,
    position = position ?: 0
)
