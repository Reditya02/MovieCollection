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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviecollection.R
import com.example.moviecollection.core.theme.MovieCollectionTheme

@Composable
fun CompMovieCard(
    text: String
) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column {

//            val painter = rememberAsyncImagePainter(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(posterImage)
//                    .size(Size.ORIGINAL)
//                    .build()
//            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = text
            )
        }
    }
}

@Preview
@Composable
fun CompMovieCardPreview() {
    MovieCollectionTheme {
        Surface {
            CompMovieCard("test")
        }
    }
}