package com.example.moviecollection.domain.repository

import com.example.moviecollection.data.response.ListGenreResponse
import com.example.moviecollection.domain.state.UIState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getListGenre(): Flow<UIState<ListGenreResponse>>
}