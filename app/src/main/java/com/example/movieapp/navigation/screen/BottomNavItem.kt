package com.example.movieapp.navigation.screen

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.R

sealed class BottomNavItem(
    val route:String,
    val icon:ImageVector,
    val title:String,
    val onclick:(String)->Unit,
    val contentDescription:String,
    val context: Context) {

    class Home(context: Context):BottomNavItem(Screen.Home.route,
        Icons.Outlined.Home,
        context.getString(R.string.home),
        onclick = {},
        context.getString(R.string.home),
        context)
    class Search(context: Context):BottomNavItem(Screen.Search.route,
        title =context.getString(R.string.search),
        icon = Icons.Outlined.Search,
        onclick = {},
        contentDescription =context.getString(R.string.search),
        context =context)
    class Downloads(context: Context):BottomNavItem(Screen.Downloads.route,
        Icons.Outlined.ArrowDropDown,
        context.getString(R.string.downloads),
        onclick = {},
        context.getString(R.string.downloads),
        context)
    class Settings(context: Context):BottomNavItem(Screen.Profile.route,
        Icons.Outlined.Settings,
        context.getString(R.string.settings),
        onclick = {},
        context.getString(R.string.settings),
        context)


}