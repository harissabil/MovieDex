package com.harissabil.moviedex.feature.movie.domain.usecase

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMovieListUseCase {
    operator fun invoke(): Flow<Resource<List<Movie>>>
}