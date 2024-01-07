package com.harissabil.moviedex.feature.movie_detail.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.harissabil.moviedex.core.feature_api.FeatureApi

interface MovieDetailApi : FeatureApi

class MovieDetailApiImpl : MovieDetailApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackbarHostState: SnackbarHostState
    ) {
        InternalMovieDetailApi.registerGraph(navController, navGraphBuilder, snackbarHostState)
    }
}