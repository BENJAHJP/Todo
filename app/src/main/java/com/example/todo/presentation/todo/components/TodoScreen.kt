package com.example.todo.presentation.todo.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.presentation.todo.TodoScreenEvents
import com.example.todo.presentation.todo.TodoViewModel
import com.example.todo.presentation.uiEvents.UiEvents
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    onNavigate: (UiEvents.OnNavigate) -> Unit,
    viewModel: TodoViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {

    val todos = viewModel.todos.collectAsState(initial = emptyList())
    
    LaunchedEffect(key1 = true){
        viewModel.uiEvents.collectLatest { event ->
            when(event){
                is UiEvents.OnNavigate -> {
                    onNavigate(event)
                }
                is UiEvents.OnShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.actions
                    )
                }
                else -> null
            }
        }
    }
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(TodoScreenEvents.OnCreateTodoClicked) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(){
                items(todos.value){ todo -> 
                    SingleTodo(todo = todo, viewModel = viewModel)
                }
            }
        }
    }
}