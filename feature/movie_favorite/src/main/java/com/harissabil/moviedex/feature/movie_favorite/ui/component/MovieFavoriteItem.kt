package com.harissabil.moviedex.feature.movie_favorite.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.harissabil.moviedex.core.common.theme.spacing
import com.harissabil.moviedex.core.database.domain.model.MovieFavorite

@Composable
fun MovieFavoriteItem(
    movie: MovieFavorite,
    onReviewDelete: (MovieFavorite) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = movie.imageUrl)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(movie.movieId.toString())
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(movie.movieId.toString())
                    .allowHardware(false)
                    .allowRgb565(true)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "Cover Image from Anime ${movie.title}",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(
                    colorMatrix = ColorMatrix().apply {
                        setToSaturation(sat = 0.85F)
                    }
                ),
                filterQuality = FilterQuality.Medium,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.small))
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 8.dp
                    )
            ) {
                Row {
                    Column(
                        modifier = Modifier.weight(1f, fill = true)
                    ) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    IconButton(onClick = { onReviewDelete(movie) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Review"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}