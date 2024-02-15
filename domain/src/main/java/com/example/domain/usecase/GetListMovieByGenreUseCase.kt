package com.example.domain.usecase

import com.example.domain.repository.IRepository
import javax.inject.Inject

class GetListMovieByGenreUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke(genre: Int) = repository.getListMovieByGenre(genre)
}