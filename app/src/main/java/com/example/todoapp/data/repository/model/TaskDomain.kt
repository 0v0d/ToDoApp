package com.example.todoapp.data.repository.model

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
