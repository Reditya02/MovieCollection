package com.example.moviecollection.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.domain.model.MovieModel
import com.example.moviecollection.R
import com.example.util.Const.POSTER_URL

@Composable
fun CompMovieCard(
    modifier: Modifier = Modifier,
    movie: MovieModel
) {
    Card(
        modifier = modifier
    ) {
        Column {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data("${POSTER_URL}${movie.posterPath}")
                    .error(R.drawable.ic_launcher_background)
                    .size(Size.ORIGINAL)
                    .build()
            )

            if (painter.state is AsyncImagePainter.State.Loading) {
                CompLoading(modifier = Modifier.aspectRatio(0.67f))
            } else {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.67f),
                    painter = painter,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier.padding(8.dp),
                text = movie.title + "\n",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}