package com.harissabil.moviedex.feature.movie.ui.screen

import com.harissabil.moviedex.feature.movie.domain.model.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = ""
)
