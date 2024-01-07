package com.harissabil.moviedex.feature.movie.domain.di

import com.harissabil.moviedex.feature.movie.domain.repository.MovieRepository
import com.harissabil.moviedex.feature.movie.domain.usecase.GetMovieListUseCase
import com.harissabil.moviedex.feature.movie.domain.usecase.GetMovieListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovieListUseCase {
        return GetMovieListUseCaseImpl(movieRepository)
    }
}