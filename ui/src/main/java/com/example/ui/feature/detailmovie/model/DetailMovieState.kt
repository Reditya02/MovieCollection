package com.example.ui.feature.detailmovie.model

import com.example.domain.model.DetailMovieModel

data class DetailMovieState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: DetailMovieModel = DetailMovieModel(
        originalLanguage = "",
        imdbId = "",
        video = false,
        title = "",
        backdropPath = "",
        revenue = 0,
        genres = listOf(),
        popularity = 0,
        id = 0,
        voteCount = 0,
        budget = 0,
        overview = "",
        originalTitle = "",
        runtime = 0,
        posterPath = "",
        releaseDate = "",
        voteAverage = 0,
        tagline = "",
        adult = false,
        homepage = "",
        status = ""
    ),
)