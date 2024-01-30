package com.example.moviecollection.data.repository

import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.domain.repository.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSources: RemoteDataSources
) : IRepository {
    override fun getListGenre() = dataSources.getListGenre()
}