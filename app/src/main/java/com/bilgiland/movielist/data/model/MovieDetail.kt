package com.bilgiland.movielist.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val adult: Boolean,
    @field:SerializedName("backdrop_path")
    val backdropPath: String,
    @field:SerializedName("belongs_to_collection")
    val belongsToCollection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @field:SerializedName("imdb_id")
    val imdbId: String,
    @field:SerializedName("original_language")
    val originalLanguage: String,
    @field:SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @field:SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    @field:SerializedName("tagline")
    val tagLine: String,
    val title: String,
    val video: Boolean,
    @field:SerializedName("vote_average")
    val voteAverage: Double,
    @field:SerializedName("vote_count")
    val voteCount: Int
)