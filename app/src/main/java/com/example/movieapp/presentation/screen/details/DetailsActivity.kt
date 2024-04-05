package com.example.movieapp.presentation.screen.details

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.utils.Resource
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series_details.SeriesDetails
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.Black80
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGL
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGS
import com.example.movieapp.presentation.ui.theme.Dimens.TS_M
import com.example.movieapp.presentation.ui.theme.Dimens.TS_S
import com.example.movieapp.presentation.ui.theme.Green80
import com.example.movieapp.presentation.ui.theme.MovieAppTheme
import com.example.movieapp.presentation.ui.theme.TextColor
import com.example.movieapp.util.DTYPE
import dagger.hilt.android.AndroidEntryPoint
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.SimpleYouTubePlayerOptionsBuilder
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayer
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayerHostState
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubePlayerState
import io.github.ilyapavlovskii.multiplatform.youtubeplayer.YouTubeVideoId
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id=intent.getIntExtra("id",0)
        val type = intent.getIntExtra("type",0)
        setContent {
            MovieAppTheme {
                val detailsViewModel:DetailsViewModel = hiltViewModel()
                LaunchedEffect(true) {
                   detailsViewModel.fetchDetails(id,type)
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(topBar = {
                        Row(
                            Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .background(Color.Black)
                                .padding(PDNGS),
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "back",
                                Modifier
                                    .size(25.dp)
                                    .clickable {
                                        finish()
                                    },
                                tint = TextColor)
                        }
                    }) {
                        Column(
                            Modifier
                                .padding(it)
                                .background(Black80)
                                .fillMaxSize()
                                .padding(PDNGS)) {

                            Log.e("type",type.toString())
                            when(type)
                            {
                                DTYPE.Series.type->{
                                    val seriesDetails by detailsViewModel.seriesDetails.collectAsState()
                                    SDetails(seriesDetails)
                                }
                                DTYPE.Movie.type->
                                {
                                    val movieDetails by detailsViewModel.movieDetails.collectAsState()
                                    MDetails(movieDetails)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SDetails(seriesDetails: Resource<SeriesDetails>)
{
    val coroutineScope = rememberCoroutineScope()
    val hostState = remember { YouTubePlayerHostState() }

    when(val state = hostState.currentState) {
        is YouTubePlayerState.Error -> {
            Text(text = "Error: ${state.message}")
        }
        YouTubePlayerState.Idle -> {
            // Do nothing, waiting for initialization
        }
        is YouTubePlayerState.Playing -> {
            // Update UI button states
        }

        YouTubePlayerState.Ready -> coroutineScope.apply {
            launch {
                seriesDetails.data?.youtubeKey?.let { YouTubeVideoId(it) }
                    ?.let { hostState.loadVideo(it) }
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()) {
        if(seriesDetails.data?.youtubeKey?.isNotEmpty() == true)
            YouTubePlayer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                hostState = hostState,
                options = SimpleYouTubePlayerOptionsBuilder.builder {
                    autoplay(true)
                    controls(false)
                    rel(false)
                    ivLoadPolicy(false)
                    ccLoadPolicy(false)
                    fullscreen = true
                },
            )
        else
        {
            Column {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w300/${seriesDetails.data?.posterImg}")
                    .crossfade(true)
                    .build(), contentDescription = "",
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    clipToBounds = true,
                    contentScale = ContentScale.Crop)
                Text(text = "no video found",Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(PDNGS))
        Text(text = seriesDetails.data?.title.toString(),
            color = Color.LightGray, fontSize = TS_M)
        Spacer(modifier = Modifier.height(PDNGS))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(PDNGS), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = seriesDetails.data?.year.toString().substring(0..3),
                color = TextColor, fontSize = TS_S)
            Text(text = "•", color = TextColor)
            Text(text = "${seriesDetails.data?.duration}m",
                color = TextColor, fontSize = TS_S)
            Text(text = "•", color = TextColor)
            Icon(imageVector = Icons.Outlined.CheckCircle, contentDescription = "safe",
                tint = if(seriesDetails.data?.safe == true) Green80 else TextColor,
                modifier = Modifier.size(20.dp))
            Text(text = "•", color = TextColor)
            Icon(imageVector = Icons.Outlined.Share, contentDescription = "share",
                modifier = Modifier.size(20.dp),
                tint = TextColor)
            Text(text = "•", color = TextColor)
            Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_download_24), contentDescription = "downloads",
                tint = if(seriesDetails.data?.safe == true) Green80 else TextColor,
                modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(PDNGL))
        Text(text = seriesDetails.data?.description.toString(),
            fontSize = TS_M,
            color = TextColor,
            modifier = Modifier
                .padding(PDNGS)
                .verticalScroll(rememberScrollState()))
    }

}
@Composable
fun MDetails(movieDetails: Resource<MovieDetails>)
{
    val coroutineScope = rememberCoroutineScope()
    val hostState = remember { YouTubePlayerHostState() }

    when(val state = hostState.currentState) {
        is YouTubePlayerState.Error -> {
            Text(text = "Error: ${state.message}")
        }
        YouTubePlayerState.Idle -> {
            // Do nothing, waiting for initialization
        }
        is YouTubePlayerState.Playing -> {
            // Update UI button states
        }

        YouTubePlayerState.Ready -> coroutineScope.apply {
            launch {
                movieDetails.data?.youtubeKey?.let { YouTubeVideoId(it) }
                    ?.let { hostState.loadVideo(it) }
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()) {
        if(movieDetails.data?.youtubeKey?.isNotEmpty() == true)
        YouTubePlayer(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            hostState = hostState,
            options = SimpleYouTubePlayerOptionsBuilder.builder {
                autoplay(true)
                controls(false)
                rel(false)
                ivLoadPolicy(false)
                ccLoadPolicy(false)
                fullscreen = true
            },
        )
        else
        {
            Column {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w300/${movieDetails.data?.posterImg}")
                    .crossfade(true)
                    .build(), contentDescription = "",
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    clipToBounds = true,
                    contentScale = ContentScale.Crop)
                Text(text = "no video found",Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(PDNGS))
        Text(text = movieDetails.data?.title.toString(),
            color = Color.LightGray, fontSize = TS_M)
        Spacer(modifier = Modifier.height(PDNGS))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(PDNGS), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = movieDetails.data?.year.toString().substring(0..3),
                color = TextColor, fontSize = TS_S)
            Text(text = "•", color = TextColor)
            Text(text = movieDetails.data?.duration.toString(),
                color = TextColor, fontSize = TS_S)
            Text(text = "•", color = TextColor)
            Icon(imageVector = Icons.Outlined.CheckCircle, contentDescription = "safe",
                tint = if(movieDetails.data?.safe == true) Green80 else TextColor,
                modifier = Modifier.size(20.dp))
            Text(text = "•", color = TextColor)
            Icon(imageVector = Icons.Outlined.Share, contentDescription = "share",
                modifier = Modifier.size(20.dp),
                tint = TextColor)
            Text(text = "•", color = TextColor)
            Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_download_24), contentDescription = "download",
                tint = if(movieDetails.data?.safe == true) Green80 else TextColor,
                modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(PDNGL))
        Text(text = movieDetails.data?.description.toString(),
            fontSize = TS_S,
            color = TextColor,
            modifier = Modifier
                .padding(PDNGS)
                .verticalScroll(rememberScrollState()))
    }

}