package com.example.movieapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieapp.navigation.screen.BottomNavItem
import com.example.movieapp.navigation.screen.Screen
import com.example.movieapp.presentation.ui.theme.BlackGrey80
import com.example.movieapp.presentation.ui.theme.Dimens.CLPM
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGM

@Composable
fun MABtmAppBar(navController: NavController) {
    val context = LocalContext.current
    val navigationItems = listOf(BottomNavItem.Home(context),
        BottomNavItem.Search(context),
        BottomNavItem.Downloads(context),
        BottomNavItem.Profile(context))
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarDestination = navigationItems.any { it.route == currentRoute }

    if(bottomBarDestination)
    Row(modifier = Modifier.fillMaxWidth()
        .padding(PDNGM)
        .clip(RoundedCornerShape(CLPM))
        .background(BlackGrey80)
        .padding(PDNGM), horizontalArrangement = Arrangement.SpaceBetween) {
        navigationItems.forEach {
            BottomBarItem(icon = it.icon, title = it.title,
                currentRoute==it.route,
                onClick = {
                    navController.navigate(it.route){
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                },modifier=Modifier)
        }
    }
}