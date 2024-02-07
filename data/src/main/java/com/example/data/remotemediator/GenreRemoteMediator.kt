package com.example.data.remotemediator

import android.util.Log
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.datasources.RemoteDataSources
import com.example.data.local.AppDatabase
import com.example.domain.model.GenreModel
import com.example.util.Handler.remoteMediatorExceptionHandler

class GenreRemoteMediator(
    private val remoteDataSources: RemoteDataSources,
    private val database: AppDatabase
) : RemoteMediator<Int, GenreModel>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, GenreModel>): MediatorResult {
        return try {
            val response = remoteDataSources.getListGenre().sortedBy { it.name }
            val endOfPagination = response.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.genreDao().deleteAll()
                }
            }

            database.genreDao().insert(response)
            MediatorResult.Success(endOfPagination)
        } catch (exception: Exception) {
            if (database.genreDao().getCount() > 0)
                MediatorResult.Success(true)
            else
                remoteMediatorExceptionHandler(exception)
        }
    }

}