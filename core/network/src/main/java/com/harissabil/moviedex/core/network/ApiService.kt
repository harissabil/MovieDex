package com.harissabil.moviedex.core.network

import com.harissabil.moviedex.core.network.response.MovieDetailDto
import com.harissabil.moviedex.core.network.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/discover/movie")
    suspend fun getMovieList(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieListResponse

    @GET("3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailDto
}