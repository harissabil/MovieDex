package com.harissabil.moviedex.core.database.domain.di

import com.harissabil.moviedex.core.database.domain.usecase.MovieFavoriteUseCase
import com.harissabil.moviedex.core.database.domain.usecase.MovieFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun provideMovieFavoriteUseCase(movieFavoriteUseCaseImpl: MovieFavoriteUseCaseImpl):
            MovieFavoriteUseCase
}