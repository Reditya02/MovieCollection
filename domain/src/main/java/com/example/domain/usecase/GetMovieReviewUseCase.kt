package com.example.domain.usecase

import com.example.domain.repository.IRepository
import javax.inject.Inject

class GetMovieReviewUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke(id: Int) = repository.getMovieReview(id)
}