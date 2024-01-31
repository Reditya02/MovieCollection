package com.example.moviecollection.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.response.MovieResultsItem

class MoviePagingSources(
    private val dataSources: RemoteDataSources,
    private val genre: Int
) : PagingSource<Int, MovieResultsItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResultsItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResultsItem> {
        return try {
            val current = params.key ?: 1
            val movies = dataSources.getListMovieByGenre(genre, current)
            LoadResult.Page(
                data = movies.listResults,
                prevKey = if (current == 1) null else current - 1,
                nextKey = if (movies.listResults.isEmpty()) null else current + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}