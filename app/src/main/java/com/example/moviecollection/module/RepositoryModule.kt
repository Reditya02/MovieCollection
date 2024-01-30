package com.example.moviecollection.module

import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.repository.RepositoryImpl
import com.example.moviecollection.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(dataSources: RemoteDataSources) : IRepository = RepositoryImpl(dataSources)
}