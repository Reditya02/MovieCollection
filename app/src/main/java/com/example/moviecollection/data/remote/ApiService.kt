package com.example.moviecollection.data.remote

import com.example.moviecollection.data.response.ListGenreResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("genre/movie/list?language=en")
    suspend fun getListGenre(): Response<ListGenreResponse>
}