package com.harissabil.moviedex.feature.movie_detail.data.mapper

import com.harissabil.moviedex.core.database.model.MovieEntity
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite

internal fun MovieFavorite.asMovieEntity(): MovieEntity {
    return MovieEntity(
        id, movieId, title, imageUrl
    )
}

internal fun MovieEntity?.asMovieFavoriteOrNull(): MovieFavorite? {
    return if (this == null) {
        null
    } else MovieFavorite(
        id = this.id,
        movieId = this.movieId,
        title = this.title,
        imageUrl = this.imageUrl
    )
}