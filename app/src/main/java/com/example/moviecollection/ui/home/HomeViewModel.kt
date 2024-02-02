package com.example.moviecollection.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecollection.data.response.Genres
import com.example.moviecollection.domain.usecase.GetListGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListGenreUseCase: GetListGenreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private val _pagerState: MutableStateFlow<PagingData<Genres>> = MutableStateFlow(PagingData.empty())
    val pagerState: StateFlow<PagingData<Genres>> = _pagerState

    init {
        getListGenre()
    }

    private fun getListGenre() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        getListGenreUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .catch { error ->
                _state.update { it.copy(
                    isLoading = false,
                    errorMessage = error.message ?: "Error"
                ) }
            }
            .collect { result ->
                _pagerState.value = result
                _state.update { it.copy(isLoading = false) }
            }

    }
}