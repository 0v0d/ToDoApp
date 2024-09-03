package com.example.todoapp.domain.usecase

import com.example.todoapp.data.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskID: String) = repository.deleteTask(taskID)
}