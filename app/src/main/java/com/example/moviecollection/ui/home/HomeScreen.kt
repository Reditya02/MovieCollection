package com.example.moviecollection.ui.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.domain.model.GenreModel
import com.example.moviecollection.core.component.CompErrorMessage
import com.example.moviecollection.core.component.CompLoading
import com.google.gson.Gson

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToListMovie: (String) -> Unit
) {
    val pagerState: LazyPagingItems<GenreModel> = viewModel.pagerState.collectAsLazyPagingItems()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Movie Collection") }) }
    ) { paddingValues ->
        val refreshState = rememberPullRefreshState(
            refreshing = false,
            onRefresh = { viewModel.getListGenre() }
        )

        Box(modifier = Modifier
            .padding(paddingValues)
            .pullRefresh(refreshState)
        ) {

            when (val state = pagerState.loadState.refresh) {
                is LoadState.Error -> {
                    CompErrorMessage(
                        modifier = Modifier.fillMaxSize(),
                        message = state.error.message ?: "Error"
                    )
                }
                is LoadState.Loading ->
                    CompLoading()
                is LoadState.NotLoading ->
                    HomeContent(
                        data = pagerState,
                        navigateToListMovie = { navigateToListMovie(it) }
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
fun HomeContent(
    data: LazyPagingItems<GenreModel>,
    navigateToListMovie: (String) -> Unit
) {
    LazyColumn(
        content = {
            items(
                data.itemCount,
                key = data.itemKey(),
                contentType = data.itemContentType()
            ) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clip(CardDefaults.shape)
                        .clickable { navigateToListMovie(Gson().toJson(data[index])) }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = data[index]?.name ?: ""
                    )
                }
            }
        }
    )
}