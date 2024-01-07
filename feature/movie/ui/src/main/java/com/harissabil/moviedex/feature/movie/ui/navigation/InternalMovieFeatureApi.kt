package com.harissabil.moviedex.feature.movie.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.harissabil.moviedex.core.common.navigation_constants.MovieFeatures
import com.harissabil.moviedex.core.feature_api.FeatureApi
import com.harissabil.moviedex.feature.movie.ui.screen.MovieScreen
import com.harissabil.moviedex.feature.movie.ui.screen.MovieViewModel

internal object InternalMovieFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackbarHostState: SnackbarHostState
    ) {
        navGraphBuilder.navigation(
            startDestination = MovieFeatures.movieScreenRoute,
            route = MovieFeatures.nestedRoute
        ) {
            composable(MovieFeatures.movieScreenRoute) {
                val viewModel: MovieViewModel = hiltViewModel()
                MovieScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}