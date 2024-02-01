package com.example.moviecollection.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviecollection.R
import com.example.moviecollection.core.helper.Const
import com.example.moviecollection.domain.model.MovieModel

@Composable
fun CompMovieCard(
    modifier: Modifier = Modifier,
    movie: MovieModel
) {
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Column {
            val posterImage = "${Const.POSTER_URL}${movie.posterPath}"

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.67f),
                model = posterImage,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
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