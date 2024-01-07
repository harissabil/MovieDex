package com.harissabil.moviedex.navigation

import com.harissabil.moviedex.feature.movie.ui.navigation.MovieApi
import com.harissabil.moviedex.feature.movie_detail.ui.navigation.MovieDetailApi

data class NavigationProvider(
    val movieApi: MovieApi,
    val movieDetailApi: MovieDetailApi,
)
