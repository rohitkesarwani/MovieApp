package com.example.domain.usecase

import com.example.common.utils.Resource
import com.example.domain.data.movie.Movie
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series.Series
import com.example.domain.data.series_details.SeriesDetails
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun searchMovies(term:String):Flow<Resource<List<Movie>>>
    suspend fun searchSeries(term:String):Flow<Resource<List<Series>>>
}