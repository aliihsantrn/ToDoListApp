package com.aliihsan.todolistapp.data.datasource

import com.aliihsan.todolistapp.data.entity.TaskModel
import com.aliihsan.todolistapp.room.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDatasource(var dao: TaskDao) {

    suspend fun getTasks() : List<TaskModel> = withContext(Dispatchers.IO) {
        return@withContext dao.getTasks()
    }

    suspend fun searchTask(query: String) : List<TaskModel> = withContext(Dispatchers.IO) {
        return@withContext dao.searchTask(query)
    }

    suspend fun addTask(
        taskTittle: String, taskDescription: String, taskDate: String
    ) = withContext(Dispatchers.IO) {
        return@withContext dao.addTask(taskTittle, taskDescription, taskDate)
    }

    suspend fun deleteTask(id: Int) = withContext(Dispatchers.IO) {
        return@withContext dao.deleteTask(id)
    }

    suspend fun updateTask(
        id: Int, taskTittle: String, taskDescription: String, taskDate: String
    ) = withContext(Dispatchers.IO) {
        return@withContext dao.updateTask(id, taskTittle, taskDescription, taskDate)
    }


}