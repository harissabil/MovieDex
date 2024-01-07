package com.harissabil.moviedex.di

import com.harissabil.moviedex.core.database.domain.usecase.MovieFavoriteUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MovieFavoriteModuleDependencies {

    fun movieFavoriteUseCase(): MovieFavoriteUseCase
}
