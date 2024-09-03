package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.model.Task
import com.example.todoapp.domain.usecase.SaveTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskFormViewModel @Inject constructor(
    private val saveTaskUseCase: SaveTaskUseCase
) : ViewModel() {
    fun saveTask(task: Task) {
        viewModelScope.launch {
            saveTaskUseCase(task)
        }
    }
}