package com.example.todoapp.domain.usecase

import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.data.repository.model.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = repository.updateTask(task)
}
