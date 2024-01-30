package com.example.moviecollection.ui.listmovie

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecollection.core.component.CompMovieCard
import com.example.moviecollection.data.response.Genres
import com.example.moviecollection.data.response.MovieResultsItem
import com.example.moviecollection.domain.state.UIState
import com.google.gson.Gson

@Composable
fun ListMovieScreen(
    viewModel: ListMovieViewModel = hiltViewModel(),
    data: String,
    onClick: (Int) -> Unit
) {
    val arguments = Gson().fromJson(data, Genres::class.java)

    viewModel.getListMovieByGenre(arguments.id)
    val state = viewModel.state.collectAsState().value

    ListMovieContent(data = state.result, onClick = { onClick(it) })
}

@Composable
fun ListMovieContent(
    data: List<MovieResultsItem>,
    onClick: (Int) -> Unit
) {
    Scaffold {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(2),
            content = {
                items(data, key = { it.id }) {
                    CompMovieCard(
                        modifier = Modifier.clickable { onClick(it.id) },
                        movie = it
                    )
                }
            }
        )
    }
}