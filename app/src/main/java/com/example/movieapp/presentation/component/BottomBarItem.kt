package com.example.movieapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.presentation.ui.theme.BlackGrey80
import com.example.movieapp.presentation.ui.theme.Dimens.ICNSIZE_S
import com.example.movieapp.presentation.ui.theme.Dimens.TS_S
import com.example.movieapp.presentation.ui.theme.Gold80
import com.example.movieapp.presentation.ui.theme.TextColor

@Composable
fun BottomBarItem(
    icon: ImageVector,
    title: String,
    selected: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier
)
{
    Column(modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = title,
            tint = if(selected) Gold80 else TextColor,
            modifier = modifier.size(ICNSIZE_S))
        Text(text = title, color = if(selected) Gold80 else TextColor,
            fontSize = TS_S)
    }
}