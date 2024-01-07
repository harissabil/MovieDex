package com.harissabil.moviedex.feature.movie_detail.ui.di

import com.harissabil.moviedex.feature.movie_detail.ui.navigation.MovieDetailApi
import com.harissabil.moviedex.feature.movie_detail.ui.navigation.MovieDetailApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideMovieDetailApi(): MovieDetailApi {
        return MovieDetailApiImpl()
    }
}