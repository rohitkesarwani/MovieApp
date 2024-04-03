package com.example.domain.repository

import com.example.domain.data.movie.Movie
import com.example.domain.data.tv.TV

interface LocalRepository {
    fun saveMovie()
    fun saveSeries()

    fun getMovies():List<Movie>
    fun getSeries():List<TV>
}