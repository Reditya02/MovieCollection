package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.DetailMovieModel
import com.example.domain.model.GenreModel
import com.example.domain.model.ListMovieVideoModel
import com.example.domain.model.MovieModel
import com.example.domain.model.MovieReviewModel
import com.example.util.Result
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getListGenre(): Flow<PagingData<GenreModel>>

    fun getListMovieByGenre(genre: Int): Flow<PagingData<MovieModel>>

    fun getDetailMovie(id: Int): Flow<Result<DetailMovieModel>>

    fun getMovieVideo(id: Int): Flow<Result<ListMovieVideoModel>>

    fun getMovieReview(id: Int): Flow<PagingData<MovieReviewModel>>
}