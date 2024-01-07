package com.harissabil.moviedex.feature.movie_favorite.ui.screen

import com.harissabil.moviedex.core.database.domain.model.MovieFavorite

data class MovieFavoriteState(
    val data: List<MovieFavorite>? = null
)
