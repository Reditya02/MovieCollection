package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.DetailMovieModel
import com.example.domain.model.GenreModel
import com.example.domain.model.ListMovieVideoModel
import com.example.domain.model.MovieModel
import com.example.domain.model.MovieReviewModel
import com.example.util.UIState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getListGenre(): Flow<PagingData<GenreModel>>

    fun getListMovieByGenre(genre: Int): Flow<PagingData<MovieModel>>

    fun getDetailMovie(id: Int): Flow<UIState<DetailMovieModel>>

    fun getMovieVideo(id: Int): Flow<UIState<ListMovieVideoModel>>

    fun getMovieReview(id: Int): Flow<PagingData<MovieReviewModel>>
}