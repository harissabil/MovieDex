package com.harissabil.moviedex.core.database

import com.harissabil.moviedex.core.database.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    suspend fun insertMovie(movieEntity: MovieEntity) = movieDao.insertMovie(movieEntity)

    suspend fun deleteMovie(movieEntity: MovieEntity) = movieDao.deleteMovie(movieEntity)

    suspend fun isFavorite(movieId: Int) = movieDao.isFavorite(movieId)

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
}