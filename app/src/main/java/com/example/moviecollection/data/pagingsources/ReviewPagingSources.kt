package com.example.moviecollection.data.pagingsources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecollection.data.datasources.RemoteDataSources
import com.example.moviecollection.data.response.MovieReviewResultsItem

class ReviewPagingSources(
    private val dataSources: RemoteDataSources,
    private val id: Int
) : PagingSource<Int, MovieReviewResultsItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieReviewResultsItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReviewResultsItem> {
        return try {
            val current = params.key ?: 1
            val reviews = dataSources.getMovieReview(id, current)
            LoadResult.Page(
                data = reviews.results,
                prevKey = if (current == 1) null else current - 1,
                nextKey = if (reviews.results.isEmpty()) null else current + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}