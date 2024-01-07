package com.harissabil.moviedex.feature.movie.data.mapper

import com.harissabil.moviedex.core.network.response.MovieListResponse
import com.harissabil.moviedex.feature.movie.domain.model.Movie

internal fun MovieListResponse.asMovie(): List<Movie> {
    return this.results.map {
        Movie(
            id = it.id,
            title = it.title,
            imageUrl = "https://www.themoviedb.org/t/p/w440_and_h660_face" + it.poster_path
        )
    }
}