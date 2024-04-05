package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.navigation.screen.Screen
import com.example.movieapp.presentation.screen.Settings.Settings
import com.example.movieapp.presentation.screen.downloads.Downloads
import com.example.movieapp.presentation.screen.home.Home
import com.example.movieapp.presentation.screen.profile.Profile
import com.example.movieapp.presentation.screen.search.Search

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route)
    {
        composable(route=Screen.Home.route){
            Home(navHostController)
        }
        composable(route=Screen.Search.route){
            Search(navHostController)
        }

        composable(route=Screen.Downloads.route){
            Downloads(navHostController)
        }
        composable(route=Screen.Profile.route){
            Profile(navHostController)
        }

        composable(route=Screen.Settings.route){
            Settings(navHostController)
        }

    }
}