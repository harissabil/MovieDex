package com.harissabil.moviedex.feature.movie_detail.domain.model

data class MovieFavorite(
    val id: Int? = null,
    val movieId: Int,
    val title: String,
    val imageUrl: String
)
