package com.harissabil.moviedex.core.database.domain.usecase

import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteUseCase {
    suspend fun insertMovie(movieFavorite: MovieFavorite)
    suspend fun deleteMovie(movieFavorite: MovieFavorite)
    fun getAllMovies(): Flow<List<MovieFavorite>>
}