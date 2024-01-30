package com.example.moviecollection.ui.listmovie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecollection.data.response.MovieResultsItem
import com.example.moviecollection.domain.state.UIState
import com.example.moviecollection.domain.usecase.GetListMovieByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getListMovieByGenreUseCase: GetListMovieByGenreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ListMovieState())
    val state: StateFlow<ListMovieState> = _state

    fun getListMovieByGenre(genre: Int) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        getListMovieByGenreUseCase(genre).filter { it != UIState.Loading }.collectLatest { uiState ->
            when(uiState) {
                UIState.Empty -> _state.update { it.copy(errorMessage = "No data found") }
                is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message) }
                UIState.Loading -> TODO()
                is UIState.Success -> _state.update { it.copy(result = uiState.data.results) }
            }
        }
    }
}