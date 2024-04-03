package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.navigation.screen.Screen
import com.example.movieapp.presentation.screen.downloads.Downloads
import com.example.movieapp.presentation.screen.downloads.DownloadsViewModel
import com.example.movieapp.presentation.screen.home.Home
import com.example.movieapp.presentation.screen.home.HomeViewModel
import com.example.movieapp.presentation.screen.profile.Profile
import com.example.movieapp.presentation.screen.profile.ProfileViewModel
import com.example.movieapp.presentation.screen.search.Search
import com.example.movieapp.presentation.screen.search.SearchViewModel

@Composable
fun MainNavGraph(navHostController: NavHostController,
                 homeViewModel: HomeViewModel,
                 searchViewModel:SearchViewModel,
                 downloadsViewModel:DownloadsViewModel,
                 profileViewModel:ProfileViewModel
                 ) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route)
    {
        composable(route=Screen.Home.route){
            Home(navHostController,homeViewModel)
        }
        composable(route=Screen.Search.route){
            Search(navHostController,searchViewModel)
        }
        composable(route=Screen.Downloads.route){
            Downloads(navHostController,downloadsViewModel)
        }
        composable(route=Screen.Profile.route){
            Profile(navHostController,profileViewModel)
        }

    }
}