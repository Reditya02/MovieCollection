package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreModel(
    @PrimaryKey
    val id: Int,
    val name: String
)
