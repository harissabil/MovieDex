package com.harissabil.moviedex.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.harissabil.moviedex.core.common.navigation_constants.MovieFeatures

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    snackbarHostState: SnackbarHostState
) {
    NavHost(navController = navController, startDestination = MovieFeatures.nestedRoute) {
        navigationProvider.movieApi.registerGraph(
            navController, this, snackbarHostState
        )
        navigationProvider.movieDetailApi.registerGraph(
            navController, this, snackbarHostState
        )
    }
}