package com.example.ui.feature.listmovie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.domain.model.GenreModel
import com.example.domain.model.MovieModel
import com.example.ui.component.CompErrorMessage
import com.example.ui.component.CompLoading
import com.example.ui.component.CompMovieCard
import com.google.gson.Gson

@Composable
fun ListMovieScreen(
    viewModel: ListMovieViewModel = hiltViewModel(),
    data: String,
    onBackPressed: () -> Unit,
    onClick: (Int) -> Unit
) {
    val arguments = remember { Gson().fromJson(data, GenreModel::class.java) }

    LaunchedEffect(Unit) {
        viewModel.getListMovieByGenre(arguments.id)
    }

    val pagingState: LazyPagingItems<MovieModel> = viewModel.pagingState.collectAsLazyPagingItems()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { Text(text = arguments.name) },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            }

        ) }
    ) {
        val refreshState = rememberPullRefreshState(
            false,
            { viewModel.getListMovieByGenre(arguments.id) }
        )
        Box(modifier = Modifier
            .padding(it)
            .pullRefresh(refreshState)
        ) {

            when (val state = pagingState.loadState.refresh) {
                is LoadState.Error ->
                    CompErrorMessage(message = state.error.message ?: "Error")
                LoadState.Loading ->
                    CompLoading()
                is LoadState.NotLoading ->
                    ListMovieContent(
                        onClick = { onClick(it) },
                        pagingData = pagingState,
                    )
            }
            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = false,
                state = refreshState,
            )
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
                val movie = pagingData[index]
                CompMovieCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .animateItemPlacement()
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onClick(movie?.id ?: 0) },
                    movie = movie ?: MovieModel(id = 0)
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
                            CompErrorMessage(
                                message = error.error.localizedMessage ?: "Empty error"
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { CompLoading() }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = pagingData.loadState.refresh as LoadState.Error
                        item {
                            CompErrorMessage(
                                message = error.error.localizedMessage ?: "Empty error"
                            )
                        }
                    }

                }
            }
        },
    )
}