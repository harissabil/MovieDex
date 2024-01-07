package com.harissabil.moviedex.core.feature_api

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Interface FeatureApi defines methods to register a new feature as a screen in this app.
 */
interface FeatureApi {
    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        snackbarHostState: SnackbarHostState
    )
}