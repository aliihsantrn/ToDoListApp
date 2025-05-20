package com.aliihsan.todolistapp.room

import androidx.room.Dao
import androidx.room.Query
import com.aliihsan.todolistapp.data.entity.TaskModel

@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks")
    suspend fun getTasks(): List<TaskModel>

    @Query("INSERT INTO Tasks (taskTittle, taskDescription, taskDate) VALUES (:taskTittle, :taskDescription, :taskDate)")
    suspend fun addTask(taskTittle: String, taskDescription: String, taskDate: String)

    @Query("SELECT * FROM Tasks WHERE taskTittle like '%' || :query || '%'")
    suspend fun searchTask(query: String): List<TaskModel>

    @Query("DELETE FROM Tasks WHERE id = :id")
    suspend fun deleteTask(id: Int)

    @Query("UPDATE Tasks SET taskTittle = :taskTittle, taskDescription = :taskDescription, taskDate = :taskDate WHERE id = :id")
    suspend fun updateTask(id: Int, taskTittle: String, taskDescription: String, taskDate: String)
}