package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.model.GenreModel
import com.example.domain.model.MovieModel
import com.example.domain.model.RemoteKeys

@Database(
    entities = [GenreModel::class, MovieModel::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}