package com.example.data.remote

import com.example.data.response.DetailMovieResponse
import com.example.data.response.ListGenreResponse
import com.example.data.response.ListMovieByGenreResponse
import com.example.data.response.MovieReviewResponse
import com.example.data.response.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getListGenre(): ListGenreResponse

    @GET("discover/movie")
    suspend fun getListMovieByGenre(
        @Query("with_genres") genre: Int,
        @Query("page") page: Int
    ): ListMovieByGenreResponse

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
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): MovieReviewResponse
}