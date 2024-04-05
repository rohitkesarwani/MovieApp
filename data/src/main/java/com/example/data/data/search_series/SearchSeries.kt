package com.example.data.data.search_series


import com.example.data.data.movies.Movies
import com.example.domain.data.movie.Movie
import com.example.domain.data.series.Series
import com.google.gson.annotations.SerializedName

data class SearchSeries(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

fun SearchSeries.toDomainSeries(): List<Series> {
    val series = ArrayList<Series>()
    results?.forEach { it?.id?.let { it1 -> it.posterPath?.let { it2 -> Series(it1, it2) } }
        ?.let { it2 -> series.add(it2) } }
    return series
}
