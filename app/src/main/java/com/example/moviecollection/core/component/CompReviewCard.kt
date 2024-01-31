package com.example.moviecollection.core.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.moviecollection.data.response.MovieReviewResultsItem

@Composable
fun CompReviewCard(
    review: MovieReviewResultsItem
) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    val isExpandable by remember { derivedStateOf { textLayoutResult?.didOverflowHeight ?: false } }
    var isExpanded by remember { mutableStateOf(false) }
    val isButtonShown by remember { derivedStateOf { isExpandable || isExpanded } }

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .animateContentSize(),
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
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .animateContentSize(),
                maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult = it },
                text = review.content
            )
            if (isButtonShown) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = { isExpanded = !isExpanded }
                ) {
                    Text(text = if (isExpanded) "Read Less" else "Read More")
                }
            }
        }
    }
}