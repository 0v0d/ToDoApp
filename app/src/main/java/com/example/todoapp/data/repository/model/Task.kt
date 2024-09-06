package com.example.todoapp.data.repository.model

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Task(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false,
    var dueDate: Date? = null,
    val position : Int = 0
)
