package com.example.domain.data.series_details

data class SeriesDetails(
    val title:String,
    val year:String,
    val duration: Int,
    val safe: Boolean,
    val description:String,
    val youtubeKey:String,
    val posterImg:String="")