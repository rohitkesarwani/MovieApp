package com.example.movieapp.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.Resource
import com.example.domain.data.movie_details.MovieDetails
import com.example.domain.data.series_details.SeriesDetails
import com.example.domain.usecase.DetailsUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.movieapp.util.DTYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsUseCase: DetailsUseCase):ViewModel() {

    private var _movieDetails: MutableStateFlow<Resource<MovieDetails>> = MutableStateFlow(Resource.Idle())
    val movieDetails:StateFlow<Resource<MovieDetails>> = _movieDetails

    private var _seriesDetails: MutableStateFlow<Resource<SeriesDetails>> = MutableStateFlow(Resource.Idle())
    val seriesDetails:StateFlow<Resource<SeriesDetails>> = _seriesDetails

    fun fetchDetails(id:Int, type: Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
            when(type)
            {
                DTYPE.Series.type->{
                    detailsUseCase.fetchSeriesDetails(id).collectLatest {
                        _seriesDetails.emit(it)
                    }
                }
                DTYPE.Movie.type->{
                    detailsUseCase.fetchMovieDetails(id).collectLatest {
                        _movieDetails.emit(it)
                    }
                }

            }
        }
    }

}