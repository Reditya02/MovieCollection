package com.example.moviecollection.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviecollection.data.datasources.LocalDatasources
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.pagingsources.MoviePagingSources
import com.example.moviecollection.data.pagingsources.ReviewPagingSources
import com.example.moviecollection.data.remotemediator.GenreRemoteMediator
import com.example.moviecollection.data.response.Genres
import com.example.moviecollection.data.response.MovieResultsItem
import com.example.moviecollection.data.response.MovieReviewResultsItem
import com.example.moviecollection.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSources: RemoteDataSources,
    private val localDataSources: LocalDatasources
) : IRepository {
    override fun getListGenre(): Flow<PagingData<Genres>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = GenreRemoteMediator(remoteDataSources, localDataSources),
            pagingSourceFactory = { localDataSources.getListGenre() }
        ).flow

    override fun getListMovieByGenre(genre: Int): Flow<PagingData<MovieResultsItem>> {
        val factory = MoviePagingSources(remoteDataSources, genre)
        return Pager(
            config = PagingConfig(
                pageSize = 10, prefetchDistance = 5
            ),
            pagingSourceFactory = {
                factory
            }
        ).flow
    }

    override fun getDetailMovie(id: Int) = remoteDataSources.getDetailMovie(id)

    override fun getMovieVideo(id: Int) = remoteDataSources.getMovieVideo(id)

    override fun getMovieReview(id: Int): Flow<PagingData<MovieReviewResultsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, prefetchDistance = 5
            ),
            pagingSourceFactory = {
                ReviewPagingSources(remoteDataSources, id)
            }
        ).flow
    }
}