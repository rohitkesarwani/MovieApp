package com.example.data.data.tv


import com.example.domain.data.series.Series
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

fun TVS.toDomainTV(): ArrayList<Series> {
    val series = ArrayList<Series>()
    results.forEach { series.add(Series(it.id,it.posterPath)) }
    return series
}