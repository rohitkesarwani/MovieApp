package com.example.movieapp.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.common.utils.Resource
import com.example.movieapp.R
import com.example.movieapp.navigation.screen.Screen
import com.example.movieapp.presentation.component.MSitem
import com.example.movieapp.presentation.component.Shimmer
import com.example.movieapp.presentation.ui.theme.Black80
import com.example.movieapp.presentation.ui.theme.BlackGrey80
import com.example.movieapp.presentation.ui.theme.Dimens.CLPM
import com.example.movieapp.presentation.ui.theme.Dimens.ICNSIZE_M
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGL
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGM
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGS
import com.example.movieapp.presentation.ui.theme.Dimens.TOP_APP_BAR_SIZE
import com.example.movieapp.presentation.ui.theme.Dimens.TS_S
import com.example.movieapp.presentation.ui.theme.TextColor
import com.example.movieapp.util.DTYPE

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Home(navController: NavController,homeViewModel:HomeViewModel = hiltViewModel()) {

    var searchText by rememberSaveable {
        mutableStateOf("")
    }
    var isSearchView by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(Modifier.fillMaxSize(),
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Black80)
                .padding(PDNGL)
                .height(TOP_APP_BAR_SIZE),
                verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = searchText, onValueChange = {
                    if(it.isNotEmpty())
                    {
                        searchText=it
                        isSearchView=true
                        homeViewModel.search(it)
                   }
                    else
                    {
                        searchText=""
                        isSearchView=false
                    }
                }, colors =
                OutlinedTextFieldDefaults.colors(
                    cursorColor = TextColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ), singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(CLPM))
                        .background(BlackGrey80)
                        .weight(1f),
                    shape = RoundedCornerShape(CLPM),
                    placeholder = { Text(text = stringResource(id = R.string.search_for_movies_series_and_more),
                        color = TextColor,
                        fontSize = TS_S)},
                    trailingIcon = {
                        if(isSearchView)
                            Icon(imageVector = Icons.Outlined.Close, contentDescription = "close",
                                modifier = Modifier.size(25.dp).clickable {
                                    searchText=""
                                    isSearchView=false
                                })
                    }
                )
                Spacer(modifier = Modifier.width(PDNGL))
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "",
                    tint = TextColor, modifier = Modifier
                        .size(ICNSIZE_M)
                        .clip(RoundedCornerShape(CLPM))
                        .background(BlackGrey80)
                        .padding(PDNGM)
                        .clickable { navController.navigate(Screen.Settings.route){
                            popUpTo(Screen.Home.route)
                            launchSingleTop=true
                        } })
            }

        }) {
            Box(modifier = Modifier.padding(it)){
                if(!isSearchView)
                    MoviesAndSeries(homeViewModel = homeViewModel)
                else
                    SearchView(homeViewModel)
            }
           
        }
    
}

@Composable
fun SearchView(homeViewModel: HomeViewModel) {
    val series by homeViewModel.searchSeries.collectAsState()
    val movies by homeViewModel.searchMovies.collectAsState()
    Column(
        Modifier
            .background(Black80)
            .padding(PDNGM)
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())) {
        when(series)
        {
            is Resource.Idle->{
                Shimmer()
            }
            is Resource.Loading->{
                Shimmer()
            }
            is Resource.Success->{

                Text(text = stringResource(id = R.string.featured_series))
                Spacer(modifier = Modifier.height(PDNGS))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.heightIn(max = 600.dp)
                ) {
                    items(series.data?.size?:0) { ind ->
                        series.data?.get(ind)?.let { it1 -> MSitem(it1.img, it1.id,DTYPE.Series.type) }
                    }
                }
            }
            is Resource.Error->{
                Text(text = series.message.toString())
            }
        }
        when(movies)
        {
            is Resource.Idle->{

            }
            is Resource.Loading->{

            }
            is Resource.Success->{
                Spacer(modifier = Modifier.height(PDNGS))
                Text(text = stringResource(id = R.string.featured_movies))
                Spacer(modifier = Modifier.height(PDNGS))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.heightIn(max = 600.dp)
                ) {
                    items(movies.data?.size?:0) { ind ->
                        movies.data?.get(ind)?.let { it1 -> MSitem(it1.img, it1.id,DTYPE.Movie.type) }
                    }
                }
            }
            is Resource.Error->{

            }
        }
    }
}

@Composable
fun MoviesAndSeries(homeViewModel:HomeViewModel)
{
    val series by homeViewModel.series.collectAsState()
    val movies by homeViewModel.movies.collectAsState()

    Column(
        Modifier
            .background(Black80)
            .padding(PDNGM)
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())) {
        when(series)
        {
            is Resource.Idle->{
                Shimmer()
            }
            is Resource.Loading->{
                Shimmer()
            }
            is Resource.Success->{

                Text(text = stringResource(id = R.string.featured_series))
                Spacer(modifier = Modifier.height(PDNGS))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.heightIn(max = 600.dp)
                ) {
                    items(series.data?.size?:0) { ind ->
                        series.data?.get(ind)?.let { it1 -> MSitem(it1.img, it1.id,DTYPE.Series.type) }
                    }
                }
            }
            is Resource.Error->{
                Text(text = series.message.toString())
            }
        }
        when(movies)
        {
            is Resource.Idle->{

            }
            is Resource.Loading->{

            }
            is Resource.Success->{
                Spacer(modifier = Modifier.height(PDNGS))
                Text(text = stringResource(id = R.string.featured_movies))
                Spacer(modifier = Modifier.height(PDNGS))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier.heightIn(max = 600.dp)
                ) {
                    items(movies.data?.size?:0) { ind ->
                        movies.data?.get(ind)?.let { it1 -> MSitem(it1.img, it1.id,DTYPE.Movie.type) }
                    }
                }
            }
            is Resource.Error->{

            }
        }
    }
}