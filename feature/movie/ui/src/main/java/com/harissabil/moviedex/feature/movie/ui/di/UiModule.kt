package com.harissabil.moviedex.feature.movie.ui.di

import com.harissabil.moviedex.feature.movie.ui.navigation.MovieApi
import com.harissabil.moviedex.feature.movie.ui.navigation.MovieApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideMovieApi(): MovieApi {
        return MovieApiImpl()
    }
}