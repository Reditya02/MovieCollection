package com.example.moviecollection.data.remotemediator

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviecollection.data.datasources.LocalDataSources
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.local.AppDatabase
import com.example.moviecollection.data.response.Genres

class GenreRemoteMediator(
    private val remoteDataSources: RemoteDataSources,
    private val database: AppDatabase
) : RemoteMediator<Int, Genres>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Genres>): MediatorResult {
        return try {
            val response = remoteDataSources.getListGenre().genres.sortedBy { it.name }
            val endOfPagination = response.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.genreDao().deleteAll()
                }
            }

            database.genreDao().insert(response)
            MediatorResult.Success(endOfPagination)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

}