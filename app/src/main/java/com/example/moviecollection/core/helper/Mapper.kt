package com.example.moviecollection.core.helper

import com.example.moviecollection.data.response.MovieResultsItem
import com.example.moviecollection.domain.model.MovieModel

object Mapper {
    fun MovieResultsItem.mapToMovieModel() = MovieModel(
        id = id,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        video = video,
        title = title,
        genreIds = genreIds!!.joinToString(separator = "|"),
        posterPath = posterPath,
        backdropPath = backdropPath,
        popularity = popularity ?: 0.0,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        adult = adult,
        voteCount = voteCount

    )

    fun List<MovieResultsItem>.mapToListMovieModel() = map {
        it.mapToMovieModel()
    }
}