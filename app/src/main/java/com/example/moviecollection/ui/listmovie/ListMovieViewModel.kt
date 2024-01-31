package com.example.moviecollection.ui.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecollection.data.response.MovieResultsItem
import com.example.moviecollection.domain.usecase.GetListMovieByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _pagingState = MutableStateFlow<PagingData<MovieResultsItem>>(PagingData.empty())
    val pagingState: StateFlow<PagingData<MovieResultsItem>> = _pagingState

    fun getListMovieByGenre(genre: Int) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        getListMovieByGenreUseCase(genre)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { result ->
                _state.update { it.copy(isLoading = false) }
                _pagingState.value = result
            }
    }
}