package com.example.movieapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.movieapp.presentation.ui.theme.Dimens.CLPM
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGS
import com.example.movieapp.presentation.ui.theme.TextColor
import com.valentinilk.shimmer.shimmer

@Composable
fun Shimmer()
{

    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
        modifier = Modifier.height(600.dp)) {
        items(18)
        {
            Box(
                modifier = Modifier
                    .padding(PDNGS)
                    .size(128.dp)
                    .shimmer()
                    .clip(RoundedCornerShape(CLPM))
                    .background(TextColor)
                    .padding(PDNGS),
                contentAlignment = Alignment.Center
            ) {
            }
        }
    }
}