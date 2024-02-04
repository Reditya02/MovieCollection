package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasources.LocalDataSources
import com.example.data.datasources.RemoteDataSources
import com.example.data.local.AppDatabase
import com.example.data.pagingsources.ReviewPagingSources
import com.example.data.remotemediator.GenreRemoteMediator
import com.example.data.remotemediator.MovieRemoteMediator
import com.example.domain.model.GenreModel
import com.example.domain.model.MovieModel
import com.example.domain.model.MovieReviewModel
import com.example.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSources: RemoteDataSources,
    private val localDataSources: LocalDataSources,
    private val database: AppDatabase
) : IRepository {
    override fun getListGenre(): Flow<PagingData<GenreModel>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = GenreRemoteMediator(remoteDataSources, database),
            pagingSourceFactory = { localDataSources.getListGenre() }
        ).flow

    override fun getListMovieByGenre(genre: Int): Flow<PagingData<MovieModel>> =
        Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 4),
            remoteMediator = MovieRemoteMediator(remoteDataSources, database, genre),
            pagingSourceFactory = { localDataSources.getMovieByGenre(genre) }
        ).flow

    override fun getDetailMovie(id: Int) = remoteDataSources.getDetailMovie(id)

    override fun getMovieVideo(id: Int) = remoteDataSources.getMovieVideo(id)

    override fun getMovieReview(id: Int): Flow<PagingData<MovieReviewModel>> {
        return Pager(
            config = PagingConfig( pageSize = 10, prefetchDistance = 5 ),
            pagingSourceFactory = { ReviewPagingSources(remoteDataSources, id) }
        ).flow
    }
}