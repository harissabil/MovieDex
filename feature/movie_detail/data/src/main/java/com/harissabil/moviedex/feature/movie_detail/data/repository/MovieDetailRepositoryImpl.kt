package com.harissabil.moviedex.feature.movie_detail.data.repository

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.core.database.LocalDataSource
import com.harissabil.moviedex.core.network.NetworkDataSource
import com.harissabil.moviedex.feature.movie_detail.data.mapper.asMovieDetail
import com.harissabil.moviedex.feature.movie_detail.data.mapper.asMovieEntity
import com.harissabil.moviedex.feature.movie_detail.data.mapper.asMovieFavoriteOrNull
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieDetail
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite
import com.harissabil.moviedex.feature.movie_detail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : MovieDetailRepository {
    override fun getMovieDetail(id: String): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(networkDataSource.getMovieDetail(id).first().asMovieDetail()))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override suspend fun isFavorite(movieId: Int): MovieFavorite? {
        return localDataSource.isFavorite(movieId).asMovieFavoriteOrNull()
    }

    override suspend fun addToFavorite(movieFavorite: MovieFavorite) {
        localDataSource.insertMovie(movieFavorite.asMovieEntity())
    }

    override suspend fun deleteFavorite(movieFavorite: MovieFavorite) {
        localDataSource.deleteMovie(movieFavorite.asMovieEntity())
    }

}