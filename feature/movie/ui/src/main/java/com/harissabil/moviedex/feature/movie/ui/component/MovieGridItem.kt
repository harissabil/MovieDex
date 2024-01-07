package com.harissabil.moviedex.feature.movie.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.harissabil.moviedex.core.common.theme.spacing
import com.harissabil.moviedex.feature.movie.domain.model.Movie

@Composable
fun MovieGridItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (String) -> Unit
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(data = movie.imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCacheKey(movie.id.toString())
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCacheKey(movie.id.toString())
            .allowHardware(false)
            .allowRgb565(true)
            .crossfade(enable = true)
            .build(),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(
            colorMatrix = ColorMatrix().apply {
                setToSaturation(sat = 0.85F)
            }
        ),
        filterQuality = FilterQuality.Low,
        contentDescription = movie.title,
        modifier = Modifier
            .width(200.dp)
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(MaterialTheme.spacing.small))
            .clickable {
                onClick(movie.id.toString())
            }
            .onGloballyPositioned {
                sizeImage = it.size
            }
            .then(modifier),
    )
}