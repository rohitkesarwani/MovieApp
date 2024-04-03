package com.example.data.network

import com.example.data.data.movies.Movies
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
        @Query("sort_by") sortBy:String="popularity.desc"):Response<Movies>
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId:String,
        @Query("language") language:String="en-US",
    )
    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(
        @Path("series_id") seriesId:String,
        @Query("language") language:String="en-US",
    )

}