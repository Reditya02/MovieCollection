package com.example.data.datasources

import com.example.data.remote.ApiService
import com.example.domain.model.DetailMovieModel
import com.example.domain.model.ListMovieVideoModel
import com.example.util.Result
import com.example.util.Handler.retrofitExceptionHandler
import com.example.data.Mapper.mapToDetailMovieModel
import com.example.data.Mapper.mapToListGenre
import com.example.data.Mapper.mapToListMovieModel
import com.example.data.Mapper.mapToListMovieVideoModel
import com.example.data.Mapper.mapToMovieReviewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSources @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getListGenre() = apiService.getListGenre().genres.mapToListGenre()

    suspend fun getListMovieByGenre(genre: Int, page: Int) =
        apiService.getListMovieByGenre(genre, page).listResults.mapToListMovieModel()

    fun getDetailMovie(id: Int): Flow<Result<DetailMovieModel>> = flow {
        emit(Result.Loading)
        val response = apiService.getDetailMovie(id)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(Result.Success(body.mapToDetailMovieModel()))
        } else {
            emit(Result.Error(response.message()))
        }
    }.catch {
        emit(retrofitExceptionHandler(it))
    }.flowOn(Dispatchers.IO)

    fun getMovieVideo(id: Int): Flow<Result<ListMovieVideoModel>> = flow {
        emit(Result.Loading)
        val response = apiService.getMovieVideo(id)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            if (body.results.isNotEmpty())
                emit(Result.Success(body.mapToListMovieVideoModel()))
            else
                emit(Result.Error("Data Not Found"))
        } else {
            emit(Result.Error(response.message()))
        }
    }.catch {
        emit(retrofitExceptionHandler(it))
    }.flowOn(Dispatchers.IO)

    suspend fun getMovieReview(id: Int, page: Int) =
        apiService.getMovieReview(id, page).results.map { it.mapToMovieReviewModel() }
}