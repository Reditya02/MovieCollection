package com.example.ui.feature.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.MovieReviewModel
import com.example.domain.usecase.GetDetailMovieUseCase
import com.example.domain.usecase.GetMovieReviewUseCase
import com.example.domain.usecase.GetMovieVideoUseCase
import com.example.ui.feature.detailmovie.model.DetailMovieState
import com.example.ui.feature.detailmovie.model.MovieVideoState
import com.example.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase,
    private val getMovieReviewUseCase: GetMovieReviewUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailMovieState())
    val state: StateFlow<DetailMovieState> = _state

    private val _videoState = MutableStateFlow(MovieVideoState())
    val videoState: StateFlow<MovieVideoState> = _videoState

    private val _reviewState = MutableStateFlow<PagingData<MovieReviewModel>>(PagingData.empty())
    val reviewState: StateFlow<PagingData<MovieReviewModel>> = _reviewState

    fun getDetailMovie(id: Int) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        delay(400)
        getDetailMovieUseCase(id).filter { it != Result.Loading }.collectLatest { result ->
            _state.update { it.copy(isLoading = false) }
            when(result) {
                is Result.Error -> _state.update { it.copy(errorMessage = result.message) }
                Result.Loading -> {}
                is Result.Success -> {
                    _state.update { it.copy(
                        result = result.data,
                        errorMessage = ""
                    ) }
                    getMovieVideo(id)
                    getMovieReview(id)
                }
            }
        }
    }

    private fun getMovieVideo(id: Int) = viewModelScope.launch {
        _videoState.update { it.copy(isLoading = true) }
        delay(400)
        getMovieVideoUseCase(id).filter { it != Result.Loading }.collectLatest { result ->
            _videoState.update { it.copy(isLoading = false) }
            when(result) {
                is Result.Error -> _videoState.update { it.copy(errorMessage = result.message) }
                Result.Loading -> {}
                is Result.Success -> _videoState.update { it.copy(
                    result = result.data.results.first { movie ->
                        movie.official == true && movie.type == "Trailer" && movie.site == "YouTube"
                    }
                ) }
            }
        }
    }

    private fun getMovieReview(id: Int) = viewModelScope.launch {
        delay(400)
        getMovieReviewUseCase(id)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {result ->
                _reviewState.value = result
            }
    }
}