package com.example.moviecollection.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecollection.core.component.CompErrorMessage
import com.example.moviecollection.core.component.CompLoading
import com.example.moviecollection.data.response.Genres
import com.google.gson.Gson

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToListMovie: (String) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    val pagerState: LazyPagingItems<Genres> = viewModel.pagerState.collectAsLazyPagingItems()

    if (state.isLoading)
        CompLoading()
    else if (state.errorMessage.isNotEmpty())
        CompErrorMessage(modifier = Modifier.fillMaxSize(), message = state.errorMessage)
    else
        HomeContent(
            data = pagerState,
            navigateToListMovie = { navigateToListMovie(it) }
        )
}

@Composable
fun HomeContent(
    data: LazyPagingItems<Genres>,
    navigateToListMovie: (String) -> Unit
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(it),
            content = {
                items(data.itemCount, key = { data[it]!!.id }) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { navigateToListMovie(Gson().toJson(data[it])) }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = data[it]!!.name
                        )
                    }
                }
            }
        )
    }
}