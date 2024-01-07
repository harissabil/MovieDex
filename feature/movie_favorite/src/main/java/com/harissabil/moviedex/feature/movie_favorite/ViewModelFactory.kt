package com.harissabil.moviedex.feature.movie_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harissabil.moviedex.core.database.domain.usecase.MovieFavoriteUseCase
import com.harissabil.moviedex.feature.movie_favorite.ui.screen.MovieFavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieFavoriteUseCase: MovieFavoriteUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(movieFavoriteUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}