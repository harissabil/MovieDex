package com.harissabil.moviedex.feature.movie_detail.domain.di

import com.harissabil.moviedex.feature.movie_detail.domain.repository.MovieDetailRepository
import com.harissabil.moviedex.feature.movie_detail.domain.usecase.FavoriteMovieUseCase
import com.harissabil.moviedex.feature.movie_detail.domain.usecase.FavoriteMovieUseCaseImpl
import com.harissabil.moviedex.feature.movie_detail.domain.usecase.GetMovieDetailUseCase
import com.harissabil.moviedex.feature.movie_detail.domain.usecase.GetMovieDetailUseCaseImpl
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
    fun provideGetMovieDetailUseCase(movieDetailRepository: MovieDetailRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCaseImpl(movieDetailRepository)
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieUseCase(movieDetailRepository: MovieDetailRepository): FavoriteMovieUseCase {
        return FavoriteMovieUseCaseImpl(movieDetailRepository)
    }
}