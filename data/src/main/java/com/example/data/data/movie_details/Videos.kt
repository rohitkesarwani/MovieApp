package com.example.data.data.movie_details


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<Result>
)