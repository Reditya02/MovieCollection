package com.example.moviecollection.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.response.Genres

class GenrePagingSources(
    private val dataSources: RemoteDataSources
) : PagingSource<Int, Genres>() {
    override fun getRefreshKey(state: PagingState<Int, Genres>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Genres> {
        return try {
            val genres = dataSources.getListGenre().genres
            LoadResult.Page(
                data = genres,
                prevKey = null,
                nextKey = null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}