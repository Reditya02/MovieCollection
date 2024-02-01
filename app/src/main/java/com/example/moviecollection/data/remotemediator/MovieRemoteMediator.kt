package com.example.moviecollection.data.remotemediator

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.moviecollection.data.datasources.LocalDataSources
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.local.AppDatabase
import com.example.moviecollection.domain.model.MovieModel

class MovieRemoteMediator(
    private val remoteDataSources: RemoteDataSources,
    private val database: AppDatabase,
    private val genre: Int
) : RemoteMediator<Int, MovieModel>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieModel>
    ): MediatorResult {
        val initialPage = 1

        return try {
            val response = remoteDataSources.getListMovieByGenre(
                genre = genre, page = initialPage
            )
            val endOfPagination = response.isEmpty()
            if (loadType == LoadType.REFRESH) {
                database.movieDao().deleteByGenre(genre)
            }
            database.movieDao().insert(response)
            MediatorResult.Success(endOfPagination)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }
}