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

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToListMovie: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val state = viewModel.state.collectAsState().value

        HomeContent(
            data = state.result,
            isLoading = state.isLoading,
            errorMessage = state.errorMessage,
            navigateToListMovie = { navigateToListMovie(it) }
        )
    }
}

@Composable
fun HomeContent(
    data: List<String>,
    isLoading: Boolean,
    errorMessage: String,
    navigateToListMovie: (String) -> Unit

) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(it),
            content = {
                items(data) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { navigateToListMovie(it) }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = it
                        )
                    }
                }
            }
        )
    }
}

val dummyList = listOf("a", "b", "c")