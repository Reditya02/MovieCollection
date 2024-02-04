package com.example.moviecollection.injection

import com.example.data.datasources.LocalDataSources
import com.example.data.datasources.RemoteDataSources
import com.example.data.local.AppDatabase
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(
        remoteDataSources: RemoteDataSources,
        localDataSources: LocalDataSources,
        database: AppDatabase
    ) : IRepository =
        RepositoryImpl(remoteDataSources, localDataSources, database)
}