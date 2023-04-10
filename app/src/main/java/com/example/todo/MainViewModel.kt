package com.example.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var num1 by mutableStateOf("")
    var num2 by mutableStateOf("")
    var answer by mutableStateOf(0)
}