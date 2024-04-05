package com.example.data.usecase

import com.example.common.utils.Resource
import com.example.data.data.search_series.SearchSeries
import com.example.data.data.searchmovies.SearchMovies
import com.example.domain.data.movie.Movie
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series.Series
import com.example.domain.data.series_details.SeriesDetails
import com.example.domain.repository.RemoteRepository
import com.example.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val remoteRepository: RemoteRepository):SearchUseCase {
    override suspend fun searchMovies(term: String): Flow<Resource<List<Movie>>> = remoteRepository.searchMovies(term)

    override suspend fun searchSeries(term: String): Flow<Resource<List<Series>>> = remoteRepository.searchSeries(term)
}