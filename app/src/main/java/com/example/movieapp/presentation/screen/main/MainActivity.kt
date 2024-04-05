package com.example.movieapp.presentation.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.navigation.MainNavGraph
import com.example.movieapp.presentation.component.MABtmAppBar
import com.example.movieapp.presentation.screen.downloads.DownloadsViewModel
import com.example.movieapp.presentation.screen.home.HomeViewModel
import com.example.movieapp.presentation.screen.profile.ProfileViewModel
import com.example.movieapp.presentation.screen.search.SearchViewModel
import com.example.movieapp.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(bottomBar ={
                        MABtmAppBar(navController)
                    } ) {it->
                        MainNavGraph(navHostController = navController)
                    }
                }
            }
        }
    }
}
