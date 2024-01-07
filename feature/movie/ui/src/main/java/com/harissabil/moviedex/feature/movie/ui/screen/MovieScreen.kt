package com.harissabil.moviedex.feature.movie.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harissabil.moviedex.core.common.component.ErrorScreen
import com.harissabil.moviedex.core.common.component.LoadingScreen
import com.harissabil.moviedex.feature.movie.ui.R
import com.harissabil.moviedex.feature.movie.ui.component.MovieGridItem
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    navController: NavController
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = {
//                        navController.navigate(MovieFavoriteFeatures.movieFavoriteScreenRoute)
                        val uri = Uri.parse("moviedex://favorite")
                        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                    }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite Screen"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Timber.d(paddingValues.toString())
        MovieContent(
            state = state,
            paddingValues = paddingValues,
            navController = navController,
            onErrorRetry = viewModel::getMovieList
        )
    }
}

@Composable
fun MovieContent(
    state: MovieState,
    paddingValues: PaddingValues,
    navController: NavController,
    onErrorRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        LoadingScreen(paddingValues = paddingValues)
    }
    if (state.error.isNotBlank()) {
        ErrorScreen(
            errorMessage = state.error,
            paddingValues = paddingValues,
            onClickRetry = onErrorRetry
        )
    }
    state.data?.let { list ->
        if (list.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Nothing found", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 96.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .then(modifier)
            ) {
                items(list.size) { index ->
                    MovieGridItem(
                        movie = list[index],
                        onClick = {
                            navController.navigate("movie_detail/${it}")
                        })
                }
            }
        }
    }
}