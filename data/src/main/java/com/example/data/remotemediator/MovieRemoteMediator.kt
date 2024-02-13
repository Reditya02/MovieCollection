package com.example.data.remotemediator

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.datasources.RemoteDataSources
import com.example.data.local.AppDatabase
import com.example.domain.model.MovieModel
import com.example.domain.model.RemoteKeys
import com.example.util.Handler.remoteMediatorExceptionHandler

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
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val response = remoteDataSources.getListMovieByGenre(
                genre = genre, page = page
            )
            val endOfPagination = response.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteRemoteKeysByGenreId(genre)
                    database.movieDao().deleteByGenre(genre)
                }
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPagination) null else page + 1
            val keys = response.map {
                RemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey, genreId = genre)
            }
            database.remoteKeysDao().insertAll(keys)

            database.movieDao().insert(response)
            MediatorResult.Success(endOfPagination)
        } catch (exception: Exception) {
            if (database.movieDao().countByGenre("%$genre%") > 0)
                MediatorResult.Success(true)
            else
                remoteMediatorExceptionHandler(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieModel>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteKeysDao().getRemoteKeysId(data.id.toString(), genre)
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieModel>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteKeysDao().getRemoteKeysId(data.id.toString(), genre)
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieModel>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getRemoteKeysId(id.toString(), genre)
            }
        }
    }
}