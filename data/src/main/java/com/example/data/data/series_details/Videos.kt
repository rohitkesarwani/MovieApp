package com.example.data.data.series_details


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Videos(
    @SerializedName("results")
    val results: List<Result?>?
)