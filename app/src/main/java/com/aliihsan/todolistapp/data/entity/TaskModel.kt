package com.aliihsan.todolistapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "Tasks")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") @NotNull
    val id: Int,
    @ColumnInfo(name = "taskTittle") @NotNull
    val taskTittle: String,
    @ColumnInfo(name = "taskDescription") @NotNull
    val taskDescription: String,
    @ColumnInfo(name = "taskDate") @NotNull
    val taskDate: String,
) : Serializable
