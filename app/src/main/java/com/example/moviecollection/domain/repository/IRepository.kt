package com.example.moviecollection.domain.repository

import androidx.paging.PagingData
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.ListGenreResponse
import com.example.moviecollection.data.response.MovieResultsItem
import com.example.moviecollection.data.response.MovieReviewResultsItem
import com.example.moviecollection.data.response.MovieVideoResponse
import com.example.moviecollection.domain.state.UIState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getListGenre(): Flow<UIState<ListGenreResponse>>
    fun getListMovieByGenre(genre: Int): Flow<PagingData<MovieResultsItem>>
    fun getDetailMovie(id: Int): Flow<UIState<DetailMovieResponse>>
    fun getMovieVideo(id: Int): Flow<UIState<MovieVideoResponse>>
    fun getMovieReview(id: Int): Flow<PagingData<MovieReviewResultsItem>>
}