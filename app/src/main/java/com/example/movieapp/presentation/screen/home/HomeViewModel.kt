package com.example.movieapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.Resource
import com.example.domain.data.movie.Movie
import com.example.domain.data.series.Series
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase:HomeUseCase,
    private val searchUseCase: SearchUseCase):ViewModel() {

    private var _series:MutableStateFlow<Resource<List<Series>>> = MutableStateFlow(Resource.Idle())
    val series:StateFlow<Resource<List<Series>>> = _series

    private var _movies:MutableStateFlow<Resource<List<Movie>>> = MutableStateFlow(Resource.Idle())
    val movies:StateFlow<Resource<List<Movie>>> = _movies

    private var searchMoviesJob:Job?=null
    private var searchSeriessJob:Job?=null

    private var _searchSeries:MutableStateFlow<Resource<List<Series>>> = MutableStateFlow(Resource.Idle())
    val searchSeries:StateFlow<Resource<List<Series>>> = _searchSeries

    private var _searchMovies:MutableStateFlow<Resource<List<Movie>>> = MutableStateFlow(Resource.Idle())
    val searchMovies:StateFlow<Resource<List<Movie>>> = _searchMovies


    init {
        getSeriesMovies()
    }
    fun getSeriesMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            homeUseCase.fetchSeries().collectLatest {
                if(!(it is Resource.Loading))
                    delay(3000)
                _series.emit(it)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            homeUseCase.fetchMovies().collectLatest {
                if(!(it is Resource.Loading))
                    delay(3000)
                _movies.emit(it)
            }
        }
    }

    fun search(term: String) {

        if(searchSeriessJob?.isActive == true)
            searchSeriessJob?.cancel()

        if(searchMoviesJob?.isActive == true)
            searchMoviesJob?.cancel()

        searchSeriessJob=viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.searchSeries(term).collectLatest {

                if(searchSeriessJob?.isActive == true)
                {
                    if(it !is Resource.Loading)
                        delay(1000)
                    _searchSeries.emit(it)
                }
            }
        }

        searchMoviesJob=viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.searchMovies(term).collectLatest {
                if(searchMoviesJob?.isActive == true)
                {
                    if(it !is Resource.Loading)
                        delay(1000)
                    _searchMovies.emit(it)
                }
            }
        }
    }

    fun clearSearch()
    {
        viewModelScope.launch(Dispatchers.IO) {
            _searchSeries.emit(Resource.Idle())
            _searchMovies.emit(Resource.Idle())
        }
    }

}