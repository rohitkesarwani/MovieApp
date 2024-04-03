package com.example.data.data.series_details


import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)