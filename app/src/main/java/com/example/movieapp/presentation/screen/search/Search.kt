package com.example.movieapp.presentation.screen.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.component.Wip

@Composable
fun Search(navHostController: NavHostController, homeViewModel: SearchViewModel= hiltViewModel()) {
    Wip()

}