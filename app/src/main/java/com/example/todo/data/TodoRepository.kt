package com.example.todo.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    fun getAllTodo(): Flow<List<Todo>>
    suspend fun getTodoById(id: Int): Todo?
}