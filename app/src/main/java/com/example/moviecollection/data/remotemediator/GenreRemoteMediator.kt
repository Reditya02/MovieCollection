package com.example.moviecollection.data.remotemediator

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.moviecollection.data.datasources.LocalDatasources
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.response.Genres

class GenreRemoteMediator(
    private val remoteDataSources: RemoteDataSources,
    private val localDatasources: LocalDatasources
) : RemoteMediator<Int, Genres>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Genres>): MediatorResult {
        return try {
            val response = remoteDataSources.getListGenre().genres
            val endOfPagination = response.isEmpty()
            if (loadType == LoadType.REFRESH) {
                localDatasources.clearGenre()
            }
            localDatasources.insertGenre(response)
            MediatorResult.Success(endOfPagination)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

}