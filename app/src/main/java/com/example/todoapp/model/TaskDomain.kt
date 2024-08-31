package com.example.todoapp.model

import com.example.todoapp.utility.DateUtility
import kotlinx.serialization.Serializable

@Serializable
data class TaskDomain(
    val id: String,
    val title: String,
    val description: String,
    val completed: Boolean,
    val dueDate: String,
    val position: Int,
) : java.io.Serializable

fun TaskDomain.toEntity() = Task(
    id = id,
    title = title,
    description = description,
    completed = completed,
    dueDate = DateUtility().parseDate(dueDate),
    position = position
)