package com.example.data.data.movies


import com.example.domain.data.movie.Movie
import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
fun Movies.toDomainMovies(results: List<Result>): ArrayList<Movie> {
    val movies = ArrayList<Movie>()
    results.forEach {movies.add(Movie(it.id,it.posterPath)) }
    return movies
}
