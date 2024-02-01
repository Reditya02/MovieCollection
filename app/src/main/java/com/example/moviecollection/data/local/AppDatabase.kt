package com.example.moviecollection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviecollection.data.response.Genres
import com.example.moviecollection.domain.model.MovieModel

@Database(
    entities = [Genres::class, MovieModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
}