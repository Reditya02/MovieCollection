package com.example.moviecollection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviecollection.data.response.Genres

@Database(
    entities = [Genres::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao() : GenreDao
}