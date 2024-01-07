package com.harissabil.moviedex.core.network

import com.harissabil.moviedex.core.network.response.MovieDetailDto
import com.harissabil.moviedex.core.network.response.MovieListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(): Flow<MovieListResponse> = flow {
        emit(apiService.getMovieList())
    }.flowOn(Dispatchers.IO)

    suspend fun getMovieDetail(id: String): Flow<MovieDetailDto> = flow {
        emit(apiService.getMovieDetail(id))
    }.flowOn(Dispatchers.IO)
}