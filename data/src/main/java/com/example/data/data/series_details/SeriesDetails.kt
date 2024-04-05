package com.example.data.data.series_details


import android.util.Log
import com.example.data.data.movie_details.MovieDetails
import com.example.data.data.movie_details.toDomainMovieDetails
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SeriesDetails(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<Any?>?,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("genres")
    val genres: List<Genre?>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("in_production")
    val inProduction: Boolean?,
    @SerializedName("languages")
    val languages: List<String?>?,
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("networks")
    val networks: List<Network?>?,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int?,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int?,
    @SerializedName("origin_country")
    val originCountry: List<String?>?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry?>?,
    @SerializedName("seasons")
    val seasons: List<Season?>?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("videos")
    val videos: Videos?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)


fun SeriesDetails.toDomainSeriesDetails():com.example.domain.data.series_details.SeriesDetails{
    Log.e("data youtube",Gson().toJson(this.videos))
    return com.example.domain.data.series_details.SeriesDetails(
        title=this.name.toString(),
        description = this.overview.toString(),
        year = this.firstAirDate.toString(),
        duration = if(episodeRunTime?.isNotEmpty() == true) episodeRunTime[0]?:0 else 0,
        safe = !(this.adult?:false),
        youtubeKey = (if(this.videos?.results?.isNotEmpty() == true) this.videos.results.get(0)?.key else "").toString(),
        posterImg = this.posterPath.toString()
    )
}