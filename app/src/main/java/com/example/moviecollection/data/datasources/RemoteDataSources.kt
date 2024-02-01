package com.example.moviecollection.data.datasources

import com.example.moviecollection.data.remote.ApiService
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.MovieVideoResponse
import com.example.moviecollection.domain.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSources @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getListGenre() = apiService.getListGenre()

    suspend fun getListMovieByGenre(genre: Int, page: Int) = apiService.getListMovieByGenre(genre, page)

    fun getDetailMovie(id: Int): Flow<UIState<DetailMovieResponse>> = flow {
        emit(UIState.Loading)
        val response = apiService.getDetailMovie(id)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(UIState.Success(body))
        } else {
            emit(UIState.Error(response.message()))
        }
    }.catch {
        when(it) {
            is UnknownHostException -> emit(UIState.Error("No Internet"))
            else -> emit(UIState.Error(it.message ?: "Error Appears"))
        }
    }.flowOn(Dispatchers.IO)

    fun getMovieVideo(id: Int): Flow<UIState<MovieVideoResponse>> = flow {
        emit(UIState.Loading)
        val response = apiService.getMovieVideo(id)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            if (body.results.isNotEmpty())
                emit(UIState.Success(body))
            else
                emit(UIState.Empty)
        } else {
            emit(UIState.Error(response.message()))
        }
    }.catch {
        when(it) {
            is UnknownHostException -> emit(UIState.Error("No Internet"))
            else -> emit(UIState.Error(it.message ?: "Error Appears"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getMovieReview(id: Int, page: Int) = apiService.getMovieReview(id, page)
}