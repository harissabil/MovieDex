package com.harissabil.moviedex.feature.movie_favorite.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import com.harissabil.moviedex.feature.movie_favorite.ui.component.MovieFavoriteItem
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    viewModel: MovieFavoriteViewModel,
    snackbarHostState: SnackbarHostState,
    onNavigateUpClick: () -> Unit
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MovieFavoriteViewModel.UIEvent.ShowSnackbar -> {
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
                        text = "Favorite",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUpClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Timber.d(paddingValues.toString())
        MovieFavoriteContent(
            state = state,
            paddingValues = paddingValues,
            onReviewDelete = viewModel::deleteFavorite
        )
    }
}

@Composable
fun MovieFavoriteContent(
    state: MovieFavoriteState,
    paddingValues: PaddingValues,
    onReviewDelete: (MovieFavorite) -> Unit,
    modifier: Modifier = Modifier
) {
    state.data?.let { list ->
        if (list.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No favorite movie added", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .animateContentSize()
                    .then(modifier)
            ) {
                items(list.size) { index ->
                    MovieFavoriteItem(
                        movie = list[index],
                        onReviewDelete = onReviewDelete,
                    )
                    Divider()
                }
            }
        }
    }
}