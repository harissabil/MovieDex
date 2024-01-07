package com.harissabil.moviedex.di

import com.harissabil.moviedex.feature.movie.ui.navigation.MovieApi
import com.harissabil.moviedex.feature.movie_detail.ui.navigation.MovieDetailApi
import com.harissabil.moviedex.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvider(
        movieApi: MovieApi,
        movieDetailApi: MovieDetailApi,
    ): NavigationProvider {
        return NavigationProvider(movieApi, movieDetailApi)
    }
}