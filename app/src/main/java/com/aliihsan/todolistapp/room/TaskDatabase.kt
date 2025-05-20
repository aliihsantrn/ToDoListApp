package com.aliihsan.todolistapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliihsan.todolistapp.data.entity.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}