package com.example.domain.repository

import com.example.common.utils.Resource
import com.example.domain.data.movie.Movie
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series_details.SeriesDetails
import com.example.domain.data.series.Series
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getMovies(): Flow<Resource<List<Movie>>>
    suspend fun getSeries():Flow<Resource<List<Series>>>

    suspend fun getMovieDetails(movieiId:Int):Flow<Resource<MovieDetails>>
    suspend fun getSeriesDetails(seriesId:Int):Flow<Resource<SeriesDetails>>
    suspend fun searchMovies(term: String): Flow<Resource<List<Movie>>>
    suspend fun searchSeries(term: String): Flow<Resource<List<Series>>>

}