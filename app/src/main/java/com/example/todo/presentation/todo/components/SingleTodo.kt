package com.example.todo.presentation.todo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.data.Todo
import com.example.todo.presentation.todo.TodoScreenEvents
import com.example.todo.presentation.todo.TodoViewModel

@Composable
fun SingleTodo(
    todo: Todo,
    viewModel: TodoViewModel
) {
    Column() {
        Row() {
            Text(
                text = todo.title
            )
            Spacer(modifier = Modifier.weight(1f))
            Checkbox(
                checked = todo.isComplete,
                onCheckedChange = { isComplete ->
                    viewModel.onEvent(TodoScreenEvents.OnIsCompleteChange(todo = todo, isComplete = isComplete))
                }
            )
        }
    }
}