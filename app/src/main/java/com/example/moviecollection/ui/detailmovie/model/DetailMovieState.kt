package com.example.moviecollection.ui.detailmovie.model

import com.example.moviecollection.data.response.BelongsToCollection
import com.example.moviecollection.data.response.DetailMovieResponse

data class DetailMovieState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val result: DetailMovieResponse = DetailMovieResponse(
        originalLanguage = "",
        imdbId = "",
        video = false,
        title = "",
        backdropPath = "",
        revenue = 0,
        genres = listOf(),
        popularity = 0,
        productionCountries = listOf(),
        id = 0,
        voteCount = 0,
        budget = 0,
        overview = "",
        originalTitle = "",
        runtime = 0,
        posterPath = "",
        spokenLanguages = listOf(),
        productionCompanies = listOf(),
        releaseDate = "",
        voteAverage = 0,
        belongsToCollection = BelongsToCollection(
            backdropPath = "",
            name = "",
            id = 0,
            posterPath = ""
        ),
        tagline = "",
        adult = false,
        homepage = "",
        status = ""
    ),
)