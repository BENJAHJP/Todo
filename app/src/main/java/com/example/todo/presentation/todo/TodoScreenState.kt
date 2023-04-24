package com.example.todo.presentation.todo

import com.example.todo.data.Todo

data class TodoScreenState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList()
)
