package com.example.movieapp.navigation.screen

sealed class Screen(val route:String) {
    object Splash:Screen("splash")
    object Home:Screen("home")
    object Details:Screen("details")
    object Search:Screen("search")
    object Downloads:Screen("downloads")
    object Profile:Screen("profile")
    object Settings:Screen("settings")

}