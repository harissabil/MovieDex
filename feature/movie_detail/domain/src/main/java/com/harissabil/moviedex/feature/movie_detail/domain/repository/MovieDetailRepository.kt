package com.harissabil.moviedex.feature.movie_detail.domain.repository

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieDetail
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(id: String): Flow<Resource<MovieDetail>>

    suspend fun isFavorite(movieId: Int): MovieFavorite?

    suspend fun addToFavorite(movieFavorite: MovieFavorite)

    suspend fun deleteFavorite(movieFavorite: MovieFavorite)
}