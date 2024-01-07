package com.harissabil.moviedex.feature.movie_detail.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie_detail.domain.model.MovieFavorite
import com.harissabil.moviedex.feature.movie_detail.domain.usecase.FavoriteMovieUseCase
import com.harissabil.moviedex.feature.movie_detail.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> get() = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    private val _isFavorited = MutableStateFlow(false)
    val isFavorited: StateFlow<Boolean> get() = _isFavorited.asStateFlow()

    init {
        savedStateHandle.getLiveData<String>("id").observeForever {
            it?.let { id ->
                getMovieDetail(id)
                isFavorite(id.toInt())
            }
        }
    }

    fun getMovieDetail(id: String) {
        getMovieDetailUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.toString())
                }

                is Resource.Success -> {
                    _state.value = MovieDetailState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun isFavorite(movieId: Int) {
        viewModelScope.launch {
            val result = favoriteMovieUseCase.isFavorite(movieId)
            Timber.e(result.toString())
            _isFavorited.value = result != null
        }
    }

    fun addToFavorite(movieFavorite: MovieFavorite) {
        viewModelScope.launch {
            favoriteMovieUseCase.insertMovie(movieFavorite)
            _eventFlow.emit(UIEvent.ShowSnackbar("Added to Favorite"))
        }
        _isFavorited.value = true
    }

    fun deleteFavorite(movieId: Int) {
        viewModelScope.launch {
            val movieFavorite = favoriteMovieUseCase.isFavorite(movieId)
            favoriteMovieUseCase.deleteMovie(movieFavorite!!)
            _eventFlow.emit(UIEvent.ShowSnackbar("Deleted from Favorite"))
        }
        _isFavorited.value = false
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}