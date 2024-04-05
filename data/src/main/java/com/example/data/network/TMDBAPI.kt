package com.example.data.network

import com.example.data.data.movie_details.MovieDetails
import com.example.data.data.movies.Movies
import com.example.data.data.search_series.SearchSeries
import com.example.data.data.searchmovies.SearchMovies
import com.example.data.data.series_details.SeriesDetails
import com.example.data.data.tv.TVS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBAPI {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("include_adult") includeAdult:Boolean=false,
        @Query("include_video") includeVideo:Boolean=true,
        @Query("language") language:String="en-US",
        @Query("page") page:Int=1,
        @Query("sort_by") sortBy:String="popularity.desc"):Response<Movies>

    @GET("discover/tv")
    suspend fun getSeries(
        @Query("include_adult") includeAdult:Boolean=false,
        @Query("include_null_first_air_dates") includeVideo:Boolean=false,
        @Query("language") language:String="en-US",
        @Query("page") page:Int=1,
        @Query("sort_by") sortBy:String="popularity.desc"):Response<TVS>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId:Int,
        @Query("append_to_response") appendToResponse:String="videos",
        @Query("language") language:String="en-US",
    ):Response<MovieDetails>

    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(
        @Path("series_id") seriesId:Int,
        @Query("append_to_response") appendToResponse:String="videos",
        @Query("language") language:String="en-US",
    ):Response<SeriesDetails>

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("query") query:String="",
        @Query("page") includeAdult:Int=1,
        @Query("language") language:String="en-US",
    ):Response<SearchSeries>

  @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query:String="",
        @Query("page") includeAdult:Int=1,
        @Query("language") language:String="en-US",
    ):Response<SearchMovies>

}