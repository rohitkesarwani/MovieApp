package com.example.movieapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movieapp.R

@Composable
fun Wip() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        val rawComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.wip))
        LottieAnimation(
            composition = rawComposition, // Use 'urlComposition' for Way 2
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.FillBounds
        )

    }
}