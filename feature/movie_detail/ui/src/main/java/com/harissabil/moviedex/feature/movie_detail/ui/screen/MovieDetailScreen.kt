package com.harissabil.moviedex.feature.movie_detail.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.harissabil.moviedex.core.common.component.ErrorScreen
import com.harissabil.moviedex.core.common.component.LoadingScreen
import com.harissabil.moviedex.core.common.theme.padding
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite
import com.harissabil.moviedex.feature.movie_detail.ui.R
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    id: String,
    viewModel: MovieDetailViewModel,
    snackbarHostState: SnackbarHostState,
    onNavigateUpClick: () -> Unit,
) {
    val state = viewModel.state.value
    val isFavorited = viewModel.isFavorited.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MovieDetailViewModel.UIEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.movie_detail),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = onNavigateUpClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Timber.d(paddingValues.toString())
        MovieDetailContent(
            state = state,
            paddingValues = paddingValues,
            onErrorRetry = {
                viewModel.getMovieDetail(id)
            },
            isFavorited = isFavorited.value,
            onFavoriteClick = {
                if (isFavorited.value) {
                    viewModel.deleteFavorite(id.toInt())
                } else {
                    viewModel.addToFavorite(
                        MovieFavorite(
                            movieId = id.toInt(),
                            title = state.data!!.title,
                            imageUrl = state.data.imageUrl
                        )
                    )
                }
            },
            modifier = Modifier
        )
    }
}

@Composable
fun MovieDetailContent(
    state: MovieDetailState,
    paddingValues: PaddingValues,
    onErrorRetry: () -> Unit,
    isFavorited: Boolean,
    onFavoriteClick: () -> Unit,
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
    state.data?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                )
                .then(modifier)
        ) {
            AsyncImage(
                model = it.imageUrl,
                contentDescription = it.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.padding.large,
                        vertical = MaterialTheme.padding.medium
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = onFavoriteClick) {
                            Icon(
                                imageVector = if (!isFavorited) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                                contentDescription = null
                            )
                        }
                    }
                    Text(
                        text = it.releaseDate,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.alpha(0.8f)
                    )
                    Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}