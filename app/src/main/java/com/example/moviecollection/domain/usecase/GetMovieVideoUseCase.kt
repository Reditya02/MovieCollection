package com.example.moviecollection.domain.usecase

import com.example.moviecollection.domain.repository.IRepository
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke(id: Int) = repository.getMovieVideo(id)
}