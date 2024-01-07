package com.harissabil.moviedex.core.database.domain.model

data class MovieFavorite(
    val id: Int,
    val movieId: Int,
    val title: String,
    val imageUrl: String
)
