package com.example.domain.usecase

import com.example.common.utils.Resource
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series_details.SeriesDetails
import kotlinx.coroutines.flow.Flow

interface DetailsUseCase {

    suspend fun fetchMovieDetails(id:Int):Flow<Resource<MovieDetails>>
    suspend fun fetchSeriesDetails(id:Int):Flow<Resource<SeriesDetails>>
}