package com.example.moviecollection.data.datasources

import android.util.Log
import com.example.moviecollection.data.remote.ApiService
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.ListGenreResponse
import com.example.moviecollection.data.response.ListMovieByGenreResponse
import com.example.moviecollection.domain.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class RemoteDataSources @Inject constructor(
    private val apiService: ApiService
) {
    fun getListGenre(): Flow<UIState<ListGenreResponse>> = flow {
        emit(UIState.Loading)
        val response = apiService.getListGenre()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            if (body.genres.isNotEmpty())
                emit(UIState.Success(body))
            else
                emit(UIState.Empty)
        } else {
            emit(UIState.Error(response.message()))
        }
    }.catch {
        emit(UIState.Error(it.message ?: "empty error"))
    }.flowOn(Dispatchers.IO)

    fun getListMovieByGenre(genre: Int): Flow<UIState<ListMovieByGenreResponse>> = flow {
        emit(UIState.Loading)
        val response = apiService.getListMovieByGenre(genre)
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
        emit(UIState.Error(it.message ?: "empty error"))
    }.flowOn(Dispatchers.IO)

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
        emit(UIState.Error(it.message ?: "empty error"))
    }.flowOn(Dispatchers.IO)
}