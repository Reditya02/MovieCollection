package com.example.moviecollection.ui.detailmovie

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.domain.state.UIState

@Composable
fun DetailMovieScreen(
    viewModel: DetailMovieViewModel = hiltViewModel(),
    id: Int
) {
    viewModel.getDetailMovie(id)
    val state = viewModel.state.collectAsState().value

    DetailMovieContent(movie = state.result)
}

@Composable
fun DetailMovieContent(
    movie: DetailMovieResponse
) {
    Column {
        Text(text = movie.title)
        Text(text = movie.id.toString())
    }
}