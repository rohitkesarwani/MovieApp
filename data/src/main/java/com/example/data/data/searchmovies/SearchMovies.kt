package com.example.data.data.searchmovies


import com.example.data.data.movies.Movies
import com.example.domain.data.movie.Movie
import com.google.gson.annotations.SerializedName

data class SearchMovies(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)


fun SearchMovies.toDomainMovies(): ArrayList<Movie> {
    val movies = ArrayList<Movie>()
    results?.forEach { it?.id?.let { it1 -> it.posterPath?.let { it2 -> Movie(it1, it2) } }
        ?.let { it2 -> movies.add(it2) } }
    return movies
}
