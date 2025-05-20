package com.aliihsan.todolistapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliihsan.todolistapp.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val repo: TaskRepository
) : ViewModel() {

    fun updateContact(id: Int, taskTittle: String, taskDescription: String, taskDate: String) {
        viewModelScope.launch {
            repo.updateTask(id, taskTittle, taskDescription, taskDate)
        }

    }
}