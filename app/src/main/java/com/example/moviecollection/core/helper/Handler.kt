package com.example.moviecollection.core.helper

import androidx.paging.RemoteMediator.MediatorResult
import java.lang.Exception
import java.net.UnknownHostException

object Handler {
    fun remoteMediatorExceptionHandler(exception: Exception) = MediatorResult.Error(
        Throwable(message = when(exception) {
            is UnknownHostException -> "No Internet"
            else -> exception.message
        })
    )
}