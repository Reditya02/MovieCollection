package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieModel(
    @PrimaryKey
    val id: Int,
    val title: String? = null,
    val genreIds: String? = null,
    val posterPath: String? = null,
    val popularity: Double = 0.0,
)
