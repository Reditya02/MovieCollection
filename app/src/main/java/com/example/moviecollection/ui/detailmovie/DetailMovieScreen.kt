package com.example.moviecollection.ui.detailmovie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.moviecollection.core.component.CompReviewCard
import com.example.moviecollection.core.helper.Const
import com.example.moviecollection.data.response.DetailMovieResponse
import com.example.moviecollection.data.response.MovieReviewResultsItem
import com.example.moviecollection.data.response.MovieVideoResultsItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun DetailMovieScreen(
    viewModel: DetailMovieViewModel = hiltViewModel(),
    id: Int
) {
    viewModel.getDetailMovie(id)
    val state = viewModel.state.collectAsState().value
    val videoState = viewModel.videoState.collectAsState().value
    val reviewState: LazyPagingItems<MovieReviewResultsItem> = viewModel.reviewState.collectAsLazyPagingItems()

    DetailMovieContent(
        movie = state.result,
        video = videoState.result,
        listReview = reviewState
    )
}

@Composable
fun DetailMovieContent(
    movie: DetailMovieResponse,
    video: MovieVideoResultsItem,
    listReview: LazyPagingItems<MovieReviewResultsItem>
) {
    LazyColumn(
        modifier = Modifier.padding(12.dp)
    ) {
        item {
            Row {
                val posterImage = "${Const.POSTER_URL}${movie.posterPath}"

                AsyncImage(
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxWidth()
                        .aspectRatio(0.67f)
                        .clip(MaterialTheme.shapes.medium),
                    model = posterImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(6f),
                    text = movie.title
                )
            }
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { DetailMovieOverview(movie = movie) }

        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { if (video.key.isNotEmpty()) {
            DetailMovieVideo(video = video)
        } }

        items(listReview.itemCount, key = { listReview[it]!!.id }) {
            CompReviewCard(review = listReview[it]!!)
        }
    }
}

@Composable
fun DetailMovieOverview(
    movie: DetailMovieResponse
) {
    Column {
        Text(text = "Overview")
        Text(text = movie.overview)
    }
}

@Composable
fun DetailMovieVideo(
    video: MovieVideoResultsItem
) {
    AndroidView(factory = {
        val view = YouTubePlayerView(it)
        view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(video.key, 0f)
                }
            }
        )
        view
    })
}