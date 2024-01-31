package com.example.moviecollection.data.repository

import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.ListMovieByGenreResponse
import com.example.moviecollection.data.response.MovieVideoResponse
import com.example.moviecollection.domain.repository.IRepository
import com.example.moviecollection.domain.state.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSources: RemoteDataSources
) : IRepository {
    override fun getListGenre() = dataSources.getListGenre()
    override fun getListMovieByGenre(genre: Int) = dataSources.getListMovieByGenre(genre)
    override fun getDetailMovie(id: Int) = dataSources.getDetailMovie(id)
    override fun getMovieVideo(id: Int) = dataSources.getMovieVideo(id)
}