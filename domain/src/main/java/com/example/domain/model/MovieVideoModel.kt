package com.example.domain.model

data class MovieVideoModel(
    val site: String?,
    val official: Boolean?,
    val type: String,
    val key: String?
)

data class ListMovieVideoModel(
    val id: Int,
    val results: List<MovieVideoModel>
)