package com.harissabil.moviedex.feature.movie_detail.domain.usecase

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    operator fun invoke(id: String): Flow<Resource<MovieDetail>>
}