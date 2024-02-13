package com.example.ui.feature.detailmovie.model

import com.example.domain.model.MovieVideoModel

data class MovieVideoState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: MovieVideoModel = MovieVideoModel(
        site = "",
        official = false,
        type = "",
        key = ""
    )
)