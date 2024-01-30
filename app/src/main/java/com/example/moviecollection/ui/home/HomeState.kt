package com.example.moviecollection.ui.home

import com.example.moviecollection.data.response.Genres

data class HomeState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val result: List<Genres> = emptyList()
)
