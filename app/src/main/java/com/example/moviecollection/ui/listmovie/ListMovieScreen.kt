package com.example.moviecollection.ui.listmovie

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
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
    onBackPressed: () -> Unit,
    onClick: (Int) -> Unit
) {
    val arguments = Gson().fromJson(data, Genres::class.java)

    LaunchedEffect(Unit) {
        viewModel.getListMovieByGenre(arguments.id)
    }

    val state = viewModel.state.collectAsState().value
    val pagingState: LazyPagingItems<MovieModel> = viewModel.pagingState.collectAsLazyPagingItems()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { Text(text = arguments.name) },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }

        ) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            if (state.isLoading)
                CompLoading()
            else if (state.errorMessage.isNotEmpty())
                CompErrorMessage(message = state.errorMessage)
            else if (pagingState.itemCount > 0)
                ListMovieContent(onClick = { onClick(it) }, pagingData = pagingState)
        }
    }
}

@Composable
fun ListMovieContent(
    onClick: (Int) -> Unit,
    pagingData: LazyPagingItems<MovieModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(
                pagingData.itemCount,
                key = pagingData.itemKey(),
                contentType = pagingData.itemContentType()
            ) { index ->
                val movie = pagingData[index]!!
                CompMovieCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .animateItemPlacement()
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onClick(movie.id) },
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