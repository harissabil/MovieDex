package com.harissabil.moviedex.core.database.domain.usecase

import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import com.harissabil.moviedex.core.database.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : MovieFavoriteUseCase {
    override suspend fun insertMovie(movieFavorite: MovieFavorite) {
        movieFavoriteRepository.insertMovie(movieFavorite)
    }

    override suspend fun deleteMovie(movieFavorite: MovieFavorite) {
        movieFavoriteRepository.deleteMovie(movieFavorite)
    }

    override fun getAllMovies(): Flow<List<MovieFavorite>> {
        return movieFavoriteRepository.getAllMovies()
    }
}