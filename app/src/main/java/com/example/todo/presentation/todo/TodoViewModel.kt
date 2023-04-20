package com.example.todo.presentation.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Todo
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.presentation.uiEvents.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel(){
    val todos = repository.getAllTodo()
    private var deletedTodo: Todo? = null
    var searchQuery by mutableStateOf("")

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents: MutableSharedFlow<UiEvents> = _uiEvents

    fun onEvent(todoScreenEvents: TodoScreenEvents){
        when(todoScreenEvents){
            is TodoScreenEvents.OnDeleteTodo -> {
                viewModelScope.launch {
                    deletedTodo = todoScreenEvents.todo
                    repository.deleteTodo(
                        todoScreenEvents.todo
                    )
                    _uiEvents.emit(UiEvents.OnShowSnackBar(
                        message = "Todo deleted",
                        actions = "Undo"
                    ))
                }
            }
            is TodoScreenEvents.OnTodoClicked -> {
                _uiEvents.emit(UiEvents.OnNavigate("?todoId=${todoScreenEvents.todo.id}"))
            }
            is TodoScreenEvents.OnCreateTodoClicked ->{

            }
            is TodoScreenEvents.OnUndoDeleteTodo ->{
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoScreenEvents.OnSearchChanged ->{
                searchQuery = todoScreenEvents.searchQuery
            }
            is TodoScreenEvents.OnSearchClicked ->{

            }

            is TodoScreenEvents.OnIsCompleteChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        todoScreenEvents.todo.copy(
                            isComplete = todoScreenEvents.isComplete
                        )
                    )
                }
            }
        }
    }
}