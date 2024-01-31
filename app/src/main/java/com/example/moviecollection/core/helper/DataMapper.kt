package com.example.moviecollection.core.helper

import com.example.moviecollection.data.response.ListGenreResponse

object DataMapper {
    fun listGenreMapper(response: ListGenreResponse): List<String> =
        response.genres.map { it.name }
}