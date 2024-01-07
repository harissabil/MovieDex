package com.harissabil.moviedex.core.database.data.di

import com.harissabil.moviedex.core.database.data.repository.MovieFavoriteRepositoryImpl
import com.harissabil.moviedex.core.database.di.DatabaseModule
import com.harissabil.moviedex.core.database.domain.repository.MovieFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieFavoriteRepository(
        movieFavoriteRepositoryImpl: MovieFavoriteRepositoryImpl
    ): MovieFavoriteRepository
}