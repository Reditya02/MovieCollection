package com.example.moviecollection.ui.detailmovie

import com.example.moviecollection.data.response.MovieVideoResultsItem

data class MovieVideoState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: MovieVideoResultsItem = MovieVideoResultsItem(
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