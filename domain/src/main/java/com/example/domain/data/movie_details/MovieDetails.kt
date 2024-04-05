package com.example.domain.data.movie_details

data class MovieDetails(
    val title:String,
    val year:String,
    val duration: Int,
    val safe: Boolean,
    val description:String,
    val youtubeKey:String,
    val posterImg:String="")