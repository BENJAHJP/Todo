package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.data.TodoDao
import com.example.todo.data.TodoDatabase
import com.example.todo.data.TodoRepository
import com.example.todo.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): TodoDatabase{
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "todo-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository{
        return TodoRepositoryImpl(todoDao)
    }
}