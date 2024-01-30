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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getListMovieByGenreUseCase: GetListMovieByGenreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UIState<List<MovieResultsItem>>>(UIState.Loading)
    val state: StateFlow<UIState<List<MovieResultsItem>>> = _state

    fun getListMovieByGenre(genre: Int) = viewModelScope.launch {
        getListMovieByGenreUseCase(genre).collectLatest { uiState ->
            if (uiState is UIState.Success) {
                Log.d("Reditya", "response viewModel ${uiState.data.results}")
                _state.update { UIState.Success(uiState.data.results) }
            }
            else
                _state.update { uiState as UIState<List<MovieResultsItem>> }
        }
    }
}