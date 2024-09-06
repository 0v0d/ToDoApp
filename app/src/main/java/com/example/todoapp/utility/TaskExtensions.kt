package com.example.todoapp.utility

import com.example.todoapp.data.repository.model.Task
import com.example.todoapp.data.repository.model.TaskDomain

fun Task.toDomain() = TaskDomain(
    id = id,
    title = title,
    description = description,
    completed = completed,
    dueDate = dueDate?.let { DateUtility().getFormattedDate(it) } ?: "",
    position = position
)

fun TaskDomain.toEntity() = Task(
    id = id,
    title = title,
    description = description,
    completed = completed,
    dueDate = DateUtility().parseDate(dueDate),
    position = position
)