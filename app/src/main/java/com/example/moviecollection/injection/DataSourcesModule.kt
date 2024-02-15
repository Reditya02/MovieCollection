package com.example.moviecollection.injection

import com.example.data.datasources.LocalDataSources
import com.example.data.datasources.RemoteDataSources
import com.example.data.local.GenreDao
import com.example.data.local.MovieDao
import com.example.data.remote.ApiService
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