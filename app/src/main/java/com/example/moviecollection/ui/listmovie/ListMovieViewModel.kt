package com.example.moviecollection.ui.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecollection.domain.model.MovieModel
import com.example.moviecollection.domain.usecase.GetListMovieByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getListMovieByGenreUseCase: GetListMovieByGenreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ListMovieState())
    val state: StateFlow<ListMovieState> = _state

    private val _pagingState = MutableStateFlow<PagingData<MovieModel>>(PagingData.empty())
    val pagingState: StateFlow<PagingData<MovieModel>> = _pagingState

    fun getListMovieByGenre(genre: Int) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        delay(400)
        getListMovieByGenreUseCase(genre)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .catch { error ->
                _state.update { it.copy(
                    isLoading = false,
                    errorMessage = error.message ?: "Error"
                ) }
            }
            .collect { result ->
                _pagingState.value = result
                _state.update { it.copy(isLoading = false) }
            }
    }
}