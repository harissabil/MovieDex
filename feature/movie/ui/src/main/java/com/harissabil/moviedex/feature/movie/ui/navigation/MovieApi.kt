package com.harissabil.moviedex.feature.movie.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.harissabil.moviedex.core.feature_api.FeatureApi

interface MovieApi : FeatureApi

class MovieApiImpl : MovieApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackbarHostState: SnackbarHostState
    ) {
        InternalMovieFeatureApi.registerGraph(
            navController, navGraphBuilder, snackbarHostState
        )
    }
}