package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.model.Task
import com.example.todoapp.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskFormViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel() {
    fun updateTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task)
            Log.d("EditTaskFormViewModel", "updateTask:$task")
        }
    }
}
