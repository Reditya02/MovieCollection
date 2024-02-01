package com.example.moviecollection.data.datasources

import com.example.moviecollection.data.local.GenreDao
import com.example.moviecollection.data.response.Genres
import javax.inject.Inject

class LocalDatasources @Inject constructor(
    private val genreDao: GenreDao
) {
    fun getListGenre() = genreDao.getAll()

    suspend fun clearGenre() = genreDao.deleteAll()

    suspend fun insertGenre(genres: List<Genres>) = genreDao.insert(genres)


}