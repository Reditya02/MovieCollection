package com.example.ui.feature.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.MovieModel
import com.example.domain.usecase.GetListMovieByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getListMovieByGenreUseCase: GetListMovieByGenreUseCase
) : ViewModel() {

    private val _pagingState = MutableStateFlow<PagingData<MovieModel>>(PagingData.empty())
    val pagingState: StateFlow<PagingData<MovieModel>> = _pagingState

    fun getListMovieByGenre(genre: Int) = viewModelScope.launch {
        delay(400)
        getListMovieByGenreUseCase(genre)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { result ->
                _pagingState.value = result
            }
    }
}