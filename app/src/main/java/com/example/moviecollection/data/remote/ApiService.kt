package com.example.moviecollection.data.remote

import com.example.moviecollection.data.response.ListGenreResponse
import com.example.moviecollection.data.response.ListMovieByGenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list?language=en")
    suspend fun getListGenre(): Response<ListGenreResponse>

    @GET("discover/movie")
    suspend fun getListMovieByGenre(
        @Query("with_genres") genre: Int
    ): Response<ListMovieByGenreResponse>
}