package com.example.movieapp.presentation.screen.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.component.Wip

@Composable
fun Profile(navHostController: NavHostController, downloadsViewModel: ProfileViewModel= hiltViewModel()) {
    Wip()
}