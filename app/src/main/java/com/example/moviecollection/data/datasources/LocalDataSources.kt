package com.example.moviecollection.data.datasources

import com.example.moviecollection.data.local.GenreDao
import com.example.moviecollection.data.local.MovieDao
import com.example.moviecollection.data.response.Genres
import com.example.moviecollection.domain.model.MovieModel
import javax.inject.Inject

class LocalDataSources @Inject constructor(
    private val genreDao: GenreDao,
    private val movieDao: MovieDao
) {
    fun getListGenre() = genreDao.getAll()

    suspend fun clearGenre() = genreDao.deleteAll()

    suspend fun insertGenre(genres: List<Genres>) = genreDao.insert(genres)

    fun getMovieByGenre(genre: Int) = movieDao.getByGenre("%$genre%")

    suspend fun deleteMovieByGenre(genre: Int) = movieDao.deleteByGenre(genre)

    suspend fun insertMovie(movies: List<MovieModel>) = movieDao.insert(movies)
}