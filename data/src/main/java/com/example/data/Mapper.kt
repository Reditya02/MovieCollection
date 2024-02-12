package com.example.data

import com.example.data.response.AuthorDetails
import com.example.data.response.DetailMovieResponse
import com.example.data.response.Genres
import com.example.data.response.MovieReviewResultsItem
import com.example.data.response.MovieVideoResponse
import com.example.data.response.MovieVideoResultsItem
import com.example.domain.model.AuthorDetailsModel
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
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        video = video,
        title = title,
        genreIds = genreIds?.joinToString(separator = "|") ?: "",
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

    private fun Genres.mapToGenreModel() = GenreModel(
        id = id,
        name = name
    )

    fun List<Genres>.mapToListGenre() = map {
        it.mapToGenreModel()
    }

    fun DetailMovieResponse.mapToDetailMovieModel() = DetailMovieModel(
        originalLanguage = originalLanguage,
        imdbId = imdbId,
        video = video,
        title = title,
        backdropPath = backdropPath,
        revenue = revenue,
        genres = genres.map { it.mapToGenreModel() },
        popularity = popularity,
        id = id,
        voteCount = voteCount,
        budget = budget,
        overview = overview,
        originalTitle = originalTitle,
        runtime = runtime,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        tagline = tagline,
        adult = adult,
        homepage = homepage,
        status = status
    )

    private fun AuthorDetails.mapToAuthorDetailsModel() = AuthorDetailsModel(
        avatarPath = avatarPath, name = name, rating = rating, username = username

    )

    fun MovieReviewResultsItem.mapToMovieReviewModel() = MovieReviewModel(
        authorDetails = authorDetails.mapToAuthorDetailsModel(),
        updatedAt = updatedAt,
        author = author,
        createdAt = createdAt,
        id = id,
        content = content,
        url = url
    )

    fun MovieVideoResponse.mapToListMovieVideoModel() = ListMovieVideoModel(
        id = id,
        results = results.map { it.mapToMovieVideoModel() }
    )

    private fun MovieVideoResultsItem.mapToMovieVideoModel() = MovieVideoModel(
        site = site,
        size = size,
        iso31661 = iso31661,
        name = name,
        official = official,
        id = id,
        type = type,
        publishedAt = publishedAt,
        iso6391 = iso6391,
        key = key
    )
}