package com.harissabil.moviedex.feature.movie_detail.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.harissabil.moviedex.core.common.navigation_constants.MovieDetailFeatures
import com.harissabil.moviedex.core.feature_api.FeatureApi
import com.harissabil.moviedex.feature.movie_detail.ui.screen.MovieDetailScreen
import com.harissabil.moviedex.feature.movie_detail.ui.screen.MovieDetailViewModel

object InternalMovieDetailApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackbarHostState: SnackbarHostState
    ) {
        navGraphBuilder.navigation(
            route = MovieDetailFeatures.nestedRoute,
            startDestination = MovieDetailFeatures.movieDetailScreenRoute
        ) {
            composable(route = MovieDetailFeatures.movieDetailScreenRoute) {
                val id = it.arguments?.getString("id")
                val viewModel: MovieDetailViewModel = hiltViewModel()
                if (id != null) {
                    MovieDetailScreen(
                        viewModel = viewModel,
                        id = id,
                        onNavigateUpClick = {
                            navController.navigateUp()
                        },
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}