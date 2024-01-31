package com.example.moviecollection.ui.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecollection.domain.state.UIState
import com.example.moviecollection.domain.usecase.GetDetailMovieUseCase
import com.example.moviecollection.domain.usecase.GetMovieReviewUseCase
import com.example.moviecollection.domain.usecase.GetMovieVideoUseCase
import com.example.moviecollection.ui.detailmovie.model.DetailMovieState
import com.example.moviecollection.ui.detailmovie.model.MovieReviewState
import com.example.moviecollection.ui.detailmovie.model.MovieVideoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _reviewState = MutableStateFlow(MovieReviewState())
    val reviewState: StateFlow<MovieReviewState> = _reviewState

    fun getDetailMovie(id: Int) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        getDetailMovieUseCase(id).filter { it != UIState.Loading }.collectLatest { uiState ->
            _state.update { it.copy(isLoading = false) }
            when(uiState) {
                UIState.Empty -> _state.update { it.copy(errorMessage = "No data found") }
                is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message) }
                UIState.Loading -> TODO()
                is UIState.Success -> {
                    _state.update { it.copy(result = uiState.data) }
                    getMovieVideo(id)
                    getMovieReview(id)
                }
            }
        }
    }

    private fun getMovieVideo(id: Int) = viewModelScope.launch {
        _videoState.update { it.copy(isLoading = true) }
        getMovieVideoUseCase(id).filter { it != UIState.Loading }.collectLatest { uiState ->
            _state.update { it.copy(isLoading = false) }
            when(uiState) {
                UIState.Empty -> _videoState.update { it.copy(errorMessage = "No data found") }
                is UIState.Error -> _videoState.update { it.copy(errorMessage = uiState.message) }
                UIState.Loading -> TODO()
                is UIState.Success -> _videoState.update { it.copy(
                    result = uiState.data.results.first {
                        it.official && it.type == "Trailer" && it.site == "YouTube"
                    }
                ) }
            }
        }
    }

    private fun getMovieReview(id: Int) = viewModelScope.launch {
        _reviewState.update { it.copy(isLoading = true) }
        getMovieReviewUseCase(id).filter { it != UIState.Loading }.collectLatest { uiState ->
            _reviewState.update { it.copy(isLoading = false) }
            when(uiState) {
                UIState.Empty -> _reviewState.update { it.copy(errorMessage = "No data found") }
                is UIState.Error -> _reviewState.update { it.copy(errorMessage = uiState.message) }
                UIState.Loading -> TODO()
                is UIState.Success -> _reviewState.update { it.copy(result = uiState.data.results) }
            }
        }
    }
}