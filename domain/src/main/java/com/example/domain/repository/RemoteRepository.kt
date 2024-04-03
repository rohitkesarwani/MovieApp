package com.example.domain.repository

import com.example.domain.data.movie.Movie
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series_details.SeriesDetails
import com.example.domain.data.tv.TV

interface RemoteRepository {
    fun getMovies():List<Movie>
    fun getSeries():List<TV>

    fun getMovieDetails():MovieDetails
    fun getSeriesDetails():SeriesDetails

}