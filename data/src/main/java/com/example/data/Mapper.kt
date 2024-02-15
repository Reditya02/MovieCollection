package com.example.data

import com.example.data.response.DetailMovieResponse
import com.example.data.response.Genres
import com.example.data.response.MovieReviewResultsItem
import com.example.data.response.MovieVideoResponse
import com.example.data.response.MovieVideoResultsItem
import com.example.domain.model.DetailMovieModel
import com.example.domain.model.GenreModel
import com.example.domain.model.ListMovieVideoModel
import com.example.domain.model.MovieModel
import com.example.domain.model.MovieReviewModel
import com.example.domain.model.MovieVideoModel
import com.example.data.response.MovieResultsItem

object Mapper {
    private fun MovieResultsItem.mapToMovieModel() = MovieModel(
        id = id,
        title = title,
        genreIds = genreIds?.joinToString(separator = "|") ?: "",
        posterPath = posterPath,
        popularity = popularity ?: 0.0,
    )

    fun List<MovieResultsItem>.mapToListMovieModel() = map {
        it.mapToMovieModel()
    }

    private fun Genres.mapToGenreModel() = GenreModel(
        id = id,
        name = name
    )

    fun List<Genres>.mapToListGenre() = map {
        it.mapToGenreModel()
    }

    fun DetailMovieResponse.mapToDetailMovieModel() = DetailMovieModel(
        title = title,
        id = id,
        overview = overview,
        posterPath = posterPath,
    )

    fun MovieReviewResultsItem.mapToMovieReviewModel() = MovieReviewModel(
        author = author,
        content = content,
    )

    fun MovieVideoResponse.mapToListMovieVideoModel() = ListMovieVideoModel(
        id = id,
        results = results.map { it.mapToMovieVideoModel() }
    )

    private fun MovieVideoResultsItem.mapToMovieVideoModel() = MovieVideoModel(
        key = key,
        site = site,
        official = official,
        type = type
    )
}