package com.example.domain.usecase

import com.example.common.utils.Resource
import com.example.domain.data.movie.Movie
import com.example.domain.data.series.Series
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    suspend fun fetchSeries():Flow<Resource<List<Series>>>
    suspend fun fetchMovies():Flow<Resource<List<Movie>>>
    suspend fun searchMovies()
}