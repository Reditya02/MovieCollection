package com.example.moviecollection.ui.listmovie

import com.example.moviecollection.data.response.MovieResultsItem

data class ListMovieState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val result: List<MovieResultsItem> = emptyList()
)
