package com.example.data.pagingsources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.datasources.RemoteDataSources
import com.example.domain.model.MovieReviewModel

class ReviewPagingSources(
    private val dataSources: RemoteDataSources,
    private val id: Int
) : PagingSource<Int, MovieReviewModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieReviewModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReviewModel> {
        return try {
            val current = params.key ?: 1
            val reviews = dataSources.getMovieReview(id, current)
            Log.d("Reditya","Review PS $reviews")
            LoadResult.Page(
                data = reviews,
                prevKey = if (current == 1) null else current - 1,
                nextKey = if (reviews.isEmpty()) null else current + 1
            )
        } catch (exception: Exception) {
            Log.d("Reditya", "Review PS Exception $exception")
            LoadResult.Error(exception)
        }
    }
}