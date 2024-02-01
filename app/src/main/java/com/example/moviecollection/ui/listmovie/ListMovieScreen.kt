package com.example.moviecollection.ui.listmovie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecollection.core.component.CompErrorMessage
import com.example.moviecollection.core.component.CompLoading
import com.example.moviecollection.core.component.CompMovieCard
import com.example.moviecollection.data.response.Genres
import com.example.moviecollection.domain.model.MovieModel
import com.google.gson.Gson

@Composable
fun ListMovieScreen(
    viewModel: ListMovieViewModel = hiltViewModel(),
    data: String,
    onClick: (Int) -> Unit
) {
    val arguments = Gson().fromJson(data, Genres::class.java)

    LaunchedEffect(Unit) {
        viewModel.getListMovieByGenre(arguments.id)
    }

    val state = viewModel.state.collectAsState().value

    val pagingState: LazyPagingItems<MovieModel> = viewModel.pagingState.collectAsLazyPagingItems()

    if (state.isLoading)
        CompLoading()
    else if (state.errorMessage.isNotEmpty())
        CompErrorMessage(message = state.errorMessage)
    else
        ListMovieContent(onClick = { onClick(it) }, pagingData = pagingState)
}

@Composable
fun ListMovieContent(
    onClick: (Int) -> Unit,
    pagingData: LazyPagingItems<MovieModel>
) {
    Scaffold { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            content = {
                items(pagingData.itemCount) { index ->
                    val movie = pagingData[index]!!
                    CompMovieCard(
                        modifier = Modifier.clickable { onClick(movie.id) },
                        movie = movie
                    )
                }
                pagingData.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { CompLoading() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = pagingData.loadState.refresh as LoadState.Error
                            item {
                                CompErrorMessage(message = error.error.localizedMessage ?: "Empty error")
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { CompLoading() }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = pagingData.loadState.refresh as LoadState.Error
                            item {
                                CompErrorMessage(message = error.error.localizedMessage ?: "Empty error")
                            }
                        }

                    }
                }
            },
        )
    }
}