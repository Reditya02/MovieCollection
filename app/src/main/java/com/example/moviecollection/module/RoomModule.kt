package com.example.moviecollection.module

import android.content.Context
import androidx.room.Room
import com.example.moviecollection.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "movie_collection_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesGenreDao(appDatabase: AppDatabase) = appDatabase.genreDao()
}