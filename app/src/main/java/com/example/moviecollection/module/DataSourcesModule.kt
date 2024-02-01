package com.example.moviecollection.module

import com.example.moviecollection.data.datasources.LocalDataSources
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.local.GenreDao
import com.example.moviecollection.data.local.MovieDao
import com.example.moviecollection.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {
    @Provides
    @Singleton
    fun providesRemoteDataSources(apiService: ApiService) = RemoteDataSources(apiService)

    @Provides
    @Singleton
    fun providesLocalDataSources(genreDao: GenreDao, movieDao: MovieDao) =
        LocalDataSources(genreDao, movieDao)
}