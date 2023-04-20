package com.example.todo.presentation.todo

import com.example.todo.data.Todo

sealed class TodoScreenEvents {
    object OnCreateTodoClicked: TodoScreenEvents()
    data class OnTodoClicked(val todo: Todo): TodoScreenEvents()
    data class OnSearchChanged(val searchQuery: String): TodoScreenEvents()
    object OnSearchClicked: TodoScreenEvents()
    data class OnIsCompleteChange(val todo: Todo, val isComplete: Boolean): TodoScreenEvents()
    data class OnDeleteTodo(val todo: Todo): TodoScreenEvents()
    object OnUndoDeleteTodo: TodoScreenEvents()
}