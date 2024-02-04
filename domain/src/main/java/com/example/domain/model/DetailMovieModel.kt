package com.example.domain.model

data class DetailMovieModel(
    val originalLanguage: String,
    val imdbId: String,
    val video: Boolean,
    val title: String,
    val backdropPath: String,
    val revenue: Int,
    val genres: List<GenreModel>,
    val popularity: Any,
    val id: Int,
    val voteCount: Int,
    val budget: Int,
    val overview: String,
    val originalTitle: String,
    val runtime: Int,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Any,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
    val status: String
)
