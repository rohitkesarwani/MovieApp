package com.example.movieapp.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.Black80
import com.example.movieapp.presentation.ui.theme.BlackGrey80
import com.example.movieapp.presentation.ui.theme.Dimens.CLPM
import com.example.movieapp.presentation.ui.theme.Dimens.CLPS
import com.example.movieapp.presentation.ui.theme.Dimens.ICNSIZE_M
import com.example.movieapp.presentation.ui.theme.Dimens.ICNSIZE_S
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGL
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGM
import com.example.movieapp.presentation.ui.theme.Dimens.PDNGS
import com.example.movieapp.presentation.ui.theme.Dimens.TOP_APP_BAR_SIZE
import com.example.movieapp.presentation.ui.theme.Dimens.TS_S
import com.example.movieapp.presentation.ui.theme.TextColor

@Composable
fun Home(navController: NavController,homeViewModel: HomeViewModel) {

    Scaffold(Modifier.fillMaxSize(),
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Black80)
                .padding(PDNGL)
                .height(TOP_APP_BAR_SIZE),
                verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = "", onValueChange = {  }, colors =
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(CLPM))
                        .background(BlackGrey80)
                        .weight(1f),
                    shape = RoundedCornerShape(CLPM),
                    placeholder = { Text(text = stringResource(id = R.string.search_for_movies_series_and_more),
                        color = TextColor,
                        fontSize = TS_S)}
                )
                Spacer(modifier = Modifier.width(PDNGL))
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "",
                    tint = TextColor, modifier = Modifier
                        .size(ICNSIZE_M)
                        .clip(RoundedCornerShape(CLPS))
                        .background(BlackGrey80)
                        .padding(PDNGM))
            }

        }) {
        Box(
            Modifier
                .padding(it)
                .background(Black80)
                .padding(PDNGS)
                .fillMaxSize()) {

        }
    }

}