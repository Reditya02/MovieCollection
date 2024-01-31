package com.example.moviecollection.data.remote

import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.ListGenreResponse
import com.example.moviecollection.data.response.ListMovieByGenreResponse
import com.example.moviecollection.data.response.MovieReviewResponse
import com.example.moviecollection.data.response.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list?language=en")
    suspend fun getListGenre(): Response<ListGenreResponse>

    @GET("discover/movie")
    suspend fun getListMovieByGenre(
        @Query("with_genres") genre: Int
    ): Response<ListMovieByGenreResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int
    ): Response<DetailMovieResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") id: Int
    ): Response<MovieVideoResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") id: Int
    ): Response<MovieReviewResponse>
}