package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.model.TaskDomain
import com.example.todoapp.domain.usecase.DeleteTaskUseCase
import com.example.todoapp.domain.usecase.GetTaskListUseCase
import com.example.todoapp.utility.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel
@Inject constructor(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _taskList = MutableStateFlow<List<TaskDomain>>(emptyList())
    val taskList = _taskList.asStateFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    fun loadTaskList() {
        viewModelScope.launch {
            try {
                getTaskListUseCase().collect { taskList ->
                    _taskList.value = taskList.map { it.toDomain() }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loadingState.value = false
            }
        }
    }

    fun deleteTask(taskID: String) {
        viewModelScope.launch {
            deleteTaskUseCase(taskID)
        }
        loadTaskList()
    }

//    fun updateTaskOrder(currentList: List<TaskDomain>) {
//        viewModelScope.launch {
//            try {
//                updateTaskOrderUseCase(currentList)
//                Log.d("TodoListViewModel", "updateTaskOrder: Success")
//                loadTaskList()
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.d("TodoListViewModel", "updateTaskOrder: Failed ${e.message}")
//            }
//        }
//    }
}
