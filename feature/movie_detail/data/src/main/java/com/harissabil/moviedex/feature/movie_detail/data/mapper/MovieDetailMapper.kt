package com.harissabil.moviedex.feature.movie_detail.data.mapper

import com.harissabil.moviedex.core.network.response.MovieDetailDto
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieDetail

internal fun MovieDetailDto.asMovieDetail(): MovieDetail {
    return MovieDetail(
        title = this.title,
        releaseDate = release_date,
        description = this.overview,
        imageUrl = "https://www.themoviedb.org/t/p/w440_and_h660_face" + this.poster_path
    )
}