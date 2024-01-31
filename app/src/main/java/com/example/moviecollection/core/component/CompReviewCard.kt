package com.example.moviecollection.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviecollection.data.response.MovieReviewResultsItem

@Composable
fun CompReviewCard(
    review: MovieReviewResultsItem
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp),
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = review.author
            )
            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = Color.Black,
                thickness = 1.dp
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = review.content
            )
        }
    }
}