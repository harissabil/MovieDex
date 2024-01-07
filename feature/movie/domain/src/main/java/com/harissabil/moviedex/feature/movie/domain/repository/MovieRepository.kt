package com.harissabil.moviedex.feature.movie.domain.repository

import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(): Flow<Resource<List<Movie>>>
}