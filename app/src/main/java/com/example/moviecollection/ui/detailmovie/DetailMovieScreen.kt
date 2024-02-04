package com.example.moviecollection.ui.detailmovie

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.domain.model.DetailMovieModel
import com.example.domain.model.MovieReviewModel
import com.example.domain.model.MovieVideoModel
import com.example.moviecollection.R
import com.example.moviecollection.core.component.CompErrorMessage
import com.example.moviecollection.core.component.CompLoading
import com.example.moviecollection.core.component.CompReviewCard
import com.example.moviecollection.ui.detailmovie.model.MovieVideoState
import com.example.util.Const.POSTER_URL
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun DetailMovieScreen(
    viewModel: DetailMovieViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    id: Int
) {
    LaunchedEffect(Unit) {
        viewModel.getDetailMovie(id)
    }

    val state = viewModel.state.collectAsState().value
    val videoState = viewModel.videoState.collectAsState().value
    val reviewState: LazyPagingItems<MovieReviewModel> = viewModel.reviewState.collectAsLazyPagingItems()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { Text(text = state.result.title) },
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
            else {
                DetailMovieContent(
                    movie = state.result,
                    video = videoState,
                    listReview = reviewState
                )
            }
        }
    }
}

@Composable
fun DetailMovieContent(
    movie: DetailMovieModel,
    video: MovieVideoState,
    listReview: LazyPagingItems<MovieReviewModel>
) {
    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
    ) {
        item(key = "header") { DetailMovieHeader(movie = movie) }

        item(key = "overview") { DetailMovieOverview(movie = movie) }
        item(key = "video") {
            Column {
                Text(text = "Trailer")
                if (video.isLoading) {
                    CompLoading(
                        modifier = Modifier
                            .aspectRatio(1f)
                    )
                } else if (video.errorMessage.isNotEmpty()) {
                    CompErrorMessage(message = video.errorMessage)
                } else {
                    DetailMovieVideo(video = video.result)
                }
            }
        }

        item(key = "review") { Text(modifier = Modifier.padding(top = 8.dp), text = "Review") }
        items(listReview.itemCount, key = listReview.itemKey(), contentType = listReview.itemContentType()) {
            CompReviewCard(
                modifier = Modifier.animateItemPlacement( tween(200) ),
                review = listReview[it]!!
            )
        }
    }
}

@Composable
fun DetailMovieHeader(
    movie: DetailMovieModel
) {
    Row {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data("${POSTER_URL}${movie.posterPath}")
                .error(R.drawable.ic_launcher_background)
                .size(Size.ORIGINAL)
                .build()
        )

        if (painter.state is AsyncImagePainter.State.Loading) {
            CompLoading(modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
                .aspectRatio(0.67f)
                .clip(MaterialTheme.shapes.medium)
            )
        }
        Image(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
                .aspectRatio(0.67f)
                .clip(MaterialTheme.shapes.medium),
            painter = painter,
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

@Composable
fun DetailMovieOverview(
    movie: DetailMovieModel
) {
    Column(
        Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = "Overview")
        Text(text = movie.overview)
    }
}

@Composable
fun DetailMovieVideo(
    video: MovieVideoModel
) {
    AndroidView(factory = {
        val view = YouTubePlayerView(it)
        view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(video.key ?: "", 0f)
                }
            }
        )
        view
    })
}