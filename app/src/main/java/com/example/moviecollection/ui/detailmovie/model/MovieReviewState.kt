package com.example.moviecollection.ui.detailmovie.model

import com.example.moviecollection.data.response.MovieReviewResultsItem

data class MovieReviewState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: List<MovieReviewResultsItem> = emptyList()
)
