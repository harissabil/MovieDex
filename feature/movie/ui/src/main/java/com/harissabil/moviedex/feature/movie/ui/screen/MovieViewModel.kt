package com.harissabil.moviedex.feature.movie.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harissabil.moviedex.core.common.Resource
import com.harissabil.moviedex.feature.movie.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieState())
    val state: State<MovieState> get() = _state

    fun getMovieList() = viewModelScope.launch {
        getMovieListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = MovieState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = MovieState(error = it.message.toString())
                }

                is Resource.Success -> {
                    _state.value = MovieState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        viewModelScope.launch {
            getMovieList()
        }
    }
}