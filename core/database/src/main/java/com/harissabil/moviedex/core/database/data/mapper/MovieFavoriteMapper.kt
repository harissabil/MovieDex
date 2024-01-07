package com.harissabil.moviedex.core.database.data.mapper

import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import com.harissabil.moviedex.core.database.model.MovieEntity

internal fun MovieFavorite.asMovieEntity(): MovieEntity {
    return MovieEntity(
        id, movieId, title, imageUrl
    )
}

internal fun List<MovieEntity>.asListMovieFavorite(): List<MovieFavorite> {
    return this.map {
        MovieFavorite(
            id = it.id!!,
            movieId = it.movieId,
            title = it.title,
            imageUrl = it.imageUrl
        )
    }
}