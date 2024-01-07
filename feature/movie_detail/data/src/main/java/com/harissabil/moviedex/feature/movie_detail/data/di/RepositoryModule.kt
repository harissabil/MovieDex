package com.harissabil.moviedex.feature.movie_detail.data.di

import com.harissabil.moviedex.core.database.LocalDataSource
import com.harissabil.moviedex.core.network.NetworkDataSource
import com.harissabil.moviedex.feature.movie_detail.data.repository.MovieDetailRepositoryImpl
import com.harissabil.moviedex.feature.movie_detail.domain.repository.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        networkDataSource: NetworkDataSource,
        localDataSource: LocalDataSource
    ): MovieDetailRepository {
        return MovieDetailRepositoryImpl(networkDataSource, localDataSource)
    }
}