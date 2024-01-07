package com.harissabil.moviedex.feature.movie_favorite.ui.di

import android.content.Context
import com.harissabil.moviedex.di.MovieFavoriteModuleDependencies
import com.harissabil.moviedex.feature.movie_favorite.MovieFavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [MovieFavoriteModuleDependencies::class])
interface MovieFavoriteComponent {

    fun inject(activity: MovieFavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: MovieFavoriteModuleDependencies): Builder
        fun build(): MovieFavoriteComponent
    }
}