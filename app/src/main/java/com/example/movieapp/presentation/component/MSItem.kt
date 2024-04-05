package com.example.movieapp.presentation.component

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.presentation.screen.details.DetailsActivity
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGS

@Composable
fun MSitem(img:String, id:Int, type: Int)
{
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data("https://image.tmdb.org/t/p/w200/$img")
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.image),
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(RoundedCornerShape(25.dp))
            .padding(PDNGS)
            .clickable {
                       context.startActivity(Intent(context,DetailsActivity::class.java)
                           .apply {
                               putExtra("id",id)
                               putExtra("type",type)
                           })
            },
        clipToBounds = true
    )
}