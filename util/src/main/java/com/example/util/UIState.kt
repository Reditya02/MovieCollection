package com.example.util

sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
}