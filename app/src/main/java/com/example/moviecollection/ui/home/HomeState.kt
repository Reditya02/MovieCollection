package com.example.moviecollection.ui.home

data class HomeState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val result: List<String> = emptyList()
)
