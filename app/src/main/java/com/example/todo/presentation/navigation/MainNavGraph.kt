package com.example.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.presentation.add_edit.components.AddEditScreen
import com.example.todo.presentation.screens.Screens
import com.example.todo.presentation.todo.components.TodoScreen

@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.TodoScreen.route
    ){
        composable(Screens.TodoScreen.route){
            TodoScreen(onNavigate = { navHostController.navigate(it.route)})
        }
        composable(Screens.AddEditScreen.route){
            AddEditScreen()
        }
    }
}