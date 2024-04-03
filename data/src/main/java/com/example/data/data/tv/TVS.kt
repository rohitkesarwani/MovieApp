package com.example.data.data.tv


import com.example.domain.data.tv.TV
import com.google.gson.annotations.SerializedName

data class TVS(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun TVS.toDomainTV(): ArrayList<TV> {
    val series = ArrayList<TV>()
    results.forEach { series.add(TV(it.id,it.posterPath)) }
    return series
}