package com.bilgiland.movielist.data.model

import com.google.gson.annotations.SerializedName

data class MovieResultModel(
    val page: Int,
    val movieModels: List<MovieModel>,
    @field:SerializedName("total_pages")
    val totalPages: Int,
    @field:SerializedName("total_results")
    val totalResults: Int
)