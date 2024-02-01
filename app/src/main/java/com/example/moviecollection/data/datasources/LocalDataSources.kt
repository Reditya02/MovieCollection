package com.example.moviecollection.data.datasources

import com.example.moviecollection.data.local.GenreDao
import com.example.moviecollection.data.local.MovieDao
import javax.inject.Inject

class LocalDataSources @Inject constructor(
    private val genreDao: GenreDao,
    private val movieDao: MovieDao
) {
    fun getListGenre() = genreDao.getAll()

    fun getMovieByGenre(genre: Int) = movieDao.getByGenre("%$genre%")
}