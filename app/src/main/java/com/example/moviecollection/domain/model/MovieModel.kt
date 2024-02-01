package com.example.moviecollection.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieModel(
    @PrimaryKey
    val id: Int,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val genreIds: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double = 0.0,
    val voteAverage: Double? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null
)
