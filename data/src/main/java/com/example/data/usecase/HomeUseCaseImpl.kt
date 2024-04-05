package com.example.data.usecase

import com.example.common.utils.Resource
import com.example.domain.data.movie.Movie
import com.example.domain.data.series.Series
import com.example.domain.repository.RemoteRepository
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeUseCaseImpl(private val remoteRepository: RemoteRepository):HomeUseCase {
    override suspend fun fetchSeries(): Flow<Resource<List<Series>>> = remoteRepository.getSeries()

    override suspend fun fetchMovies(): Flow<Resource<List<Movie>>> = remoteRepository.getMovies()

    override suspend fun searchMovies() {

    }
}