package com.example.moviecollection.core.helper

import androidx.paging.RemoteMediator.MediatorResult
import java.io.IOException
import java.lang.Exception

object Handler {
    fun remoteMediatorExceptionHandler(exception: Exception) = MediatorResult.Error(
        Throwable(message = when(exception) {
            is IOException -> "Bad Connection"
            else -> exception.message
        })
    )
}