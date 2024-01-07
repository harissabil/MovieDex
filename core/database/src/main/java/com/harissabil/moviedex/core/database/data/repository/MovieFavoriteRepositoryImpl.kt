package com.harissabil.moviedex.core.database.data.repository

import com.harissabil.moviedex.core.database.LocalDataSource
import com.harissabil.moviedex.core.database.data.mapper.asListMovieFavorite
import com.harissabil.moviedex.core.database.data.mapper.asMovieEntity
import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import com.harissabil.moviedex.core.database.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : MovieFavoriteRepository {
    override suspend fun insertMovie(movieFavorite: MovieFavorite) {
        localDataSource.insertMovie(movieFavorite.asMovieEntity())
    }

    override suspend fun deleteMovie(movieFavorite: MovieFavorite) {
        localDataSource.deleteMovie(movieFavorite.asMovieEntity())
    }

    override fun getAllMovies(): Flow<List<MovieFavorite>> {
        return localDataSource.getAllMovies().map { it.asListMovieFavorite() }
    }
}