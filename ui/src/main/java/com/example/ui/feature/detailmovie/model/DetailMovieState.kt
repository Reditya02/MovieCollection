package com.example.ui.feature.detailmovie.model

import com.example.domain.model.DetailMovieModel

data class DetailMovieState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: DetailMovieModel = DetailMovieModel(
        title = "",
        id = 0,
        overview = "",
        posterPath = "",
    ),
)