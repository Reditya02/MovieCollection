package com.example.moviecollection.ui.detailmovie.model

import com.example.domain.model.MovieVideoModel

data class MovieVideoState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: MovieVideoModel = MovieVideoModel(
        site = "",
        size = 0,
        iso31661 = "",
        name = "",
        official = false,
        id = "",
        type = "",
        publishedAt = "",
        iso6391 = "",
        key = ""
    )
)