package com.harissabil.moviedex.feature.movie_detail.ui.screen

import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: MovieDetail? = null
)
