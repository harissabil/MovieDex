package com.harissabil.moviedex.feature.movie.domain.usecase

import com.harissabil.moviedex.feature.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieListUseCase {
    override fun invoke() = movieRepository.getMovieList()
}