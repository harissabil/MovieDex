package com.harissabil.moviedex.feature.movie.data.repository

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.core.network.NetworkDataSource
import com.harissabil.moviedex.feature.movie.data.mapper.asMovie
import com.harissabil.moviedex.feature.movie.domain.model.Movie
import com.harissabil.moviedex.feature.movie.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : MovieRepository {
    override fun getMovieList(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(networkDataSource.getMovieList().first().asMovie()))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}