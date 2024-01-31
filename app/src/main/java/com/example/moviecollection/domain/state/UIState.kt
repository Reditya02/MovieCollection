package com.example.moviecollection.domain.state

sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    object Empty : UIState<Nothing>()
    object Loading : UIState<Nothing>()
}