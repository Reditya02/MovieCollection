package com.example.moviecollection.domain.repository

import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.ListGenreResponse
import com.example.moviecollection.data.response.ListMovieByGenreResponse
import com.example.moviecollection.data.response.MovieVideoResponse
import com.example.moviecollection.domain.state.UIState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getListGenre(): Flow<UIState<ListGenreResponse>>
    fun getListMovieByGenre(genre: Int): Flow<UIState<ListMovieByGenreResponse>>
    fun getDetailMovie(id: Int): Flow<UIState<DetailMovieResponse>>
    fun getMovieVideo(id: Int): Flow<UIState<MovieVideoResponse>>
}