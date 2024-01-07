package com.harissabil.moviedex.feature.movie_favorite.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.moviedex.core.database.domain.model.MovieFavorite
import com.harissabil.moviedex.core.database.domain.usecase.MovieFavoriteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MovieFavoriteViewModel(
    private val movieFavoriteUseCase: MovieFavoriteUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MovieFavoriteState())
    val state: State<MovieFavoriteState> get() = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        movieFavoriteUseCase.getAllMovies().onEach {
            _state.value = MovieFavoriteState(it)
        }.launchIn(viewModelScope)
    }

    fun deleteFavorite(movieFavorite: MovieFavorite) {
        viewModelScope.launch {
            movieFavoriteUseCase.deleteMovie(movieFavorite)
            _eventFlow.emit(UIEvent.ShowSnackbar("Deleted from Favorite"))
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}