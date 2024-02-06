package com.example.moviecollection.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.GenreModel
import com.example.domain.usecase.GetListGenreUseCase
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
class HomeViewModel @Inject constructor(
    private val getListGenreUseCase: GetListGenreUseCase
) : ViewModel() {

    private val _pagerState: MutableStateFlow<PagingData<GenreModel>> = MutableStateFlow(PagingData.empty())
    val pagerState: StateFlow<PagingData<GenreModel>> = _pagerState

    init {
        getListGenre()
    }

    fun getListGenre() = viewModelScope.launch {
        delay(400)
        getListGenreUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { result ->
                _pagerState.value = result
            }
    }
}