package com.harissabil.moviedex.core.database.domain.repository

import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteRepository {
    suspend fun insertMovie(movieFavorite: MovieFavorite)
    suspend fun deleteMovie(movieFavorite: MovieFavorite)
    fun getAllMovies(): Flow<List<MovieFavorite>>
}