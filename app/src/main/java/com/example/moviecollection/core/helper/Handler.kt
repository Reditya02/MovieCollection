package com.example.moviecollection.core.helper

import androidx.paging.RemoteMediator.MediatorResult
import com.example.moviecollection.domain.state.UIState
import java.io.IOException
import java.lang.Exception

object Handler {
    fun remoteMediatorExceptionHandler(exception: Exception) = MediatorResult.Error(
        Throwable(message = when(exception) {
            is IOException -> "Bad Connection"
            else -> exception.message
        })
    )

    fun retrofitExceptionHandler(throwable: Throwable) = UIState.Error(
        message = when(throwable) {
            is IOException -> "No Internet"
            else -> throwable.message ?: "Error"
        }
    )
}