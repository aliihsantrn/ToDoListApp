package com.aliihsan.todolistapp.data.repo

import com.aliihsan.todolistapp.data.datasource.TaskDatasource

class TaskRepository(var datasource: TaskDatasource) {

    suspend fun getTasks() = datasource.getTasks()

    suspend fun searchTask(query: String) = datasource.searchTask(query)

    suspend fun addTask(
        taskTittle: String, taskDescription: String, taskDate: String
    ) = datasource.addTask(taskTittle, taskDescription, taskDate)

    suspend fun deleteTask(id: Int) = datasource.deleteTask(id)

    suspend fun updateTask(
        id: Int, taskTittle: String, taskDescription: String, taskDate: String
    ) = datasource.updateTask(id, taskTittle, taskDescription, taskDate)
}