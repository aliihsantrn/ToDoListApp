package com.aliihsan.todolistapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliihsan.todolistapp.data.entity.TaskModel
import com.aliihsan.todolistapp.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repo: TaskRepository
) : ViewModel() {

    var taskList = MutableLiveData<List<TaskModel>>()

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            taskList.value = repo.getTasks()
        }
    }

    fun searchTask(query: String) {
        viewModelScope.launch {
            taskList.value = repo.searchTask(query)
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            repo.deleteTask(id)
            getTasks()
        }
    }

}