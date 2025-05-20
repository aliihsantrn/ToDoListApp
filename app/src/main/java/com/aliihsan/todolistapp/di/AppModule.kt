package com.aliihsan.todolistapp.di

import android.content.Context
import androidx.room.Room
import com.aliihsan.todolistapp.data.datasource.TaskDatasource
import com.aliihsan.todolistapp.data.repo.TaskRepository
import com.aliihsan.todolistapp.room.TaskDao
import com.aliihsan.todolistapp.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository(dataSource: TaskDatasource): TaskRepository {
        return TaskRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideTaskDataSource(dao: TaskDao): TaskDatasource {
        return TaskDatasource(dao)
    }

    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao {
        val db = Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "taskDb.db"
        ).createFromAsset("taskDb.sqlite").build()
        return db.getTaskDao()
    }

}