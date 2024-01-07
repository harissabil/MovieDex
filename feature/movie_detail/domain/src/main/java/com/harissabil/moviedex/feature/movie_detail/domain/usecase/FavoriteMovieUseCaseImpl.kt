package com.harissabil.moviedex.feature.movie_detail.domain.usecase

import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite
import com.harissabil.moviedex.feature.movie_detail.domain.repository.MovieDetailRepository
import javax.inject.Inject

class FavoriteMovieUseCaseImpl @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : FavoriteMovieUseCase {
    override suspend fun insertMovie(movieFavorite: MovieFavorite) {
        movieDetailRepository.addToFavorite(movieFavorite)
    }

    override suspend fun deleteMovie(movieFavorite: MovieFavorite) {
        movieDetailRepository.deleteFavorite(movieFavorite)
    }

    override suspend fun isFavorite(movieId: Int): MovieFavorite? {
        return movieDetailRepository.isFavorite(movieId)
    }
}