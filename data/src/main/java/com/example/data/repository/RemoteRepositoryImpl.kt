package com.example.data.repository

import com.example.common.utils.Resource
import com.example.data.data.movie_details.toDomainMovieDetails
import com.example.data.data.movies.toDomainMovies
import com.example.data.data.search_series.toDomainSeries
import com.example.data.data.searchmovies.toDomainMovies
import com.example.data.data.series_details.toDomainSeriesDetails
import com.example.data.data.tv.toDomainTV
import com.example.data.network.TMDBAPI
import com.example.domain.data.movie.Movie
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series.Series
import com.example.domain.data.series_details.SeriesDetails
import com.example.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val api: TMDBAPI):RemoteRepository {
    override suspend fun getMovies(): Flow<Resource<List<Movie>>> = flow{
        emit(Resource.Loading())
        try{
            val response=api.getMovies().body()?.toDomainMovies()
            emit(Resource.Success(response,"success"))
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(message = e.message))
        }
    }

    override suspend fun getSeries(): Flow<Resource<List<Series>>> = flow {
        emit(Resource.Loading())
        try{
            val response=api.getSeries().body()?.toDomainTV()
            emit(Resource.Success(response,"success"))
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(message = e.message))
        }
    }

    override suspend fun getMovieDetails(movieiId:Int): Flow<Resource<MovieDetails>> = flow {
        emit(Resource.Loading())
        try{
            val response=api.getMovieDetails(movieId = movieiId).body()?.toDomainMovieDetails()
            emit(Resource.Success(response,"success"))
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(message = e.message))
        }
    }

    override suspend fun getSeriesDetails(seriesId:Int): Flow<Resource<SeriesDetails>> = flow {
        emit(Resource.Loading())
        try{
            val response=api.getSeriesDetails(seriesId = seriesId).body()?.toDomainSeriesDetails()
            emit(Resource.Success(response,"success"))
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(message = e.message))
        }
    }

    override suspend fun searchMovies(term: String): Flow<Resource<List<Movie>>> = flow{
        emit(Resource.Loading())
        try{
            val response=api.searchMovies(query = term).body()?.toDomainMovies()
            emit(Resource.Success(response,"success"))
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(message = e.message))
        }
    }

    override suspend fun searchSeries(term: String): Flow<Resource<List<Series>>> = flow{
        emit(Resource.Loading())
        try{
            val response=api.searchSeries(query = term).body()?.toDomainSeries()
            emit(Resource.Success(response,"success"))
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(message = e.message))
        }
    }

}