package com.example.todo.presentation.add_edit

sealed class AddEditScreenEvents{
    data class OnDescriptionChanged(val description: String): AddEditScreenEvents()
    data class OnTitleChanged(val title: String): AddEditScreenEvents()
    object OnSubmit: AddEditScreenEvents()
}
