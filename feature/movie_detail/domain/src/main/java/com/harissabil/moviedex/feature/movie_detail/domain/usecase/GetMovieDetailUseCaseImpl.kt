package com.harissabil.moviedex.feature.movie_detail.domain.usecase

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieDetail
import com.harissabil.moviedex.feature.movie_detail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : GetMovieDetailUseCase {
    override fun invoke(id: String): Flow<Resource<MovieDetail>> =
        movieDetailRepository.getMovieDetail(id)
}