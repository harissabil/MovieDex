package com.harissabil.moviedex.feature.movie.data.di

import com.harissabil.moviedex.core.network.NetworkDataSource
import com.harissabil.moviedex.feature.movie.data.repository.MovieRepositoryImpl
import com.harissabil.moviedex.feature.movie.domain.repository.MovieRepository
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
    fun provideMovieRepository(networkDataSource: NetworkDataSource): MovieRepository {
        return MovieRepositoryImpl(networkDataSource)
    }
}