package com.harissabil.moviedex.feature.movie_favorite

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.harissabil.moviedex.core.common.theme.MovieDexTheme
import com.harissabil.moviedex.di.MovieFavoriteModuleDependencies
import com.harissabil.moviedex.feature.movie_favorite.ui.di.DaggerMovieFavoriteComponent
import com.harissabil.moviedex.feature.movie_favorite.ui.screen.MovieFavoriteScreen
import com.harissabil.moviedex.feature.movie_favorite.ui.screen.MovieFavoriteViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MovieFavoriteActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieFavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMovieFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    MovieFavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
        )
        setContent {
            val snackbarHostState by remember {
                mutableStateOf(SnackbarHostState())
            }

            MovieDexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieFavoriteScreen(
                        viewModel = viewModel,
                        onNavigateUpClick = {
                            onBackPressedDispatcher.onBackPressed()
                        },
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}