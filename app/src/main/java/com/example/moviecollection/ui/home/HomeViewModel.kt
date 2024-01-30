package com.example.moviecollection.ui.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.moviecollection.domain.state.UIState
import androidx.lifecycle.viewModelScope
import com.example.moviecollection.core.helper.DataMapper
import com.example.moviecollection.domain.usecase.GetListGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListGenreUseCase: GetListGenreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getListGenre()
    }

    fun getListGenre() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        getListGenreUseCase().filter { it != UIState.Loading }.collectLatest { uiState ->
            _state.update { it.copy(isLoading = false) }
            when(uiState) {
                UIState.Empty -> _state.update { it.copy(errorMessage = "No data found") }
                is UIState.Error -> _state.update { it.copy(errorMessage = uiState.message) }
                UIState.Loading -> TODO()
                is UIState.Success -> _state.update { it.copy(result = DataMapper.listGenreMapper(uiState.data)) }
            }
        }
    }
}