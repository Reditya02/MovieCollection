package com.example.moviecollection.ui.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.domain.state.UIState
import com.example.moviecollection.domain.usecase.GetDetailMovieUseCase
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
    private val getDetailMovieUseCase: GetDetailMovieUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailMovieState())
    val state: StateFlow<DetailMovieState> = _state

    fun getDetailMovie(id: Int) = viewModelScope.launch {
        _state.update { it.copy(isLoading = false) }
        getDetailMovieUseCase(id).filter { it != UIState.Loading }.collectLatest { uiState ->
            when(uiState) {
                UIState.Empty -> _state.update { it.copy(errorMessage = "No data found") }
                is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message) }
                UIState.Loading -> TODO()
                is UIState.Success -> _state.update { it.copy(result = uiState.data) }
            }
        }
    }
}