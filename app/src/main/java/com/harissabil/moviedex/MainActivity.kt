package com.harissabil.moviedex

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.harissabil.moviedex.core.common.theme.MovieDexTheme
import com.harissabil.moviedex.navigation.AppNavGraph
import com.harissabil.moviedex.navigation.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
        )
        setContent {
            MovieDexTheme {
                val navController = rememberNavController()
                val snackbarHostState by remember {
                    mutableStateOf(SnackbarHostState())
                }
                App(
                    navController = navController,
                    navigationProvider = navigationProvider,
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

@Composable
fun App(
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    snackbarHostState: SnackbarHostState
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavGraph(
            navController = navController,
            navigationProvider = navigationProvider,
            snackbarHostState = snackbarHostState
        )
    }
}