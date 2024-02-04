package com.example.domain.usecase

import com.example.domain.repository.IRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke(id: Int) = repository.getDetailMovie(id)
}