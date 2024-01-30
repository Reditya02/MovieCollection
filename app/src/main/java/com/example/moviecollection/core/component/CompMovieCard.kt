package com.example.moviecollection.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.moviecollection.R
import com.example.moviecollection.core.helper.Const
import com.example.moviecollection.core.theme.MovieCollectionTheme
import com.example.moviecollection.data.response.MovieResultsItem

@Composable
fun CompMovieCard(
    modifier: Modifier = Modifier,
    movie: MovieResultsItem
) {
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Column {
            val posterImage = "${Const.POSTER_URL}${movie.posterPath}"

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(posterImage)
                    .size(Size.ORIGINAL)
                    .build()
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = movie.title + "\n",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}