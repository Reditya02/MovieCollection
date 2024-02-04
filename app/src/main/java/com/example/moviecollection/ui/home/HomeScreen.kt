package com.example.moviecollection.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.GenreModel
import com.example.moviecollection.core.component.CompErrorMessage
import com.example.moviecollection.core.component.CompLoading
import com.google.gson.Gson

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToListMovie: (String) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    val pagerState: LazyPagingItems<GenreModel> = viewModel.pagerState.collectAsLazyPagingItems()

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    data: LazyPagingItems<GenreModel>,
    navigateToListMovie: (String) -> Unit
) {
    Scaffold (
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Movie Collection") }) }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            content = {
                items(data.itemCount, key = { index -> data[index]!!.id }) { index ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { navigateToListMovie(Gson().toJson(data[index])) }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = data[index]!!.name
                        )
                    }
                }
            }
        )
    }
}