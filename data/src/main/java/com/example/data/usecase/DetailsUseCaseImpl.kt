package com.example.data.usecase

import com.example.common.utils.Resource
import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series_details.SeriesDetails
import com.example.domain.repository.RemoteRepository
import com.example.domain.usecase.DetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsUseCaseImpl @Inject constructor(private val remoteRepository: RemoteRepository):DetailsUseCase {
    override suspend fun fetchMovieDetails(id: Int): Flow<Resource<MovieDetails>> = remoteRepository.getMovieDetails(id)

    override suspend fun fetchSeriesDetails(id: Int): Flow<Resource<SeriesDetails>> = remoteRepository.getSeriesDetails(id)
}