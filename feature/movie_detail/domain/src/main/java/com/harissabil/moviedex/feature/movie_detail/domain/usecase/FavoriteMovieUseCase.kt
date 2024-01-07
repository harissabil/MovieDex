package com.harissabil.moviedex.feature.movie_detail.domain.usecase

import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite

interface FavoriteMovieUseCase {
    suspend fun insertMovie(movieFavorite: MovieFavorite)
    suspend fun deleteMovie(movieFavorite: MovieFavorite)
    suspend fun isFavorite(movieId: Int): MovieFavorite?
}