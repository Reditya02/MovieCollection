package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val pkId: Int = 0,
    val id: String,
    val genreId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
