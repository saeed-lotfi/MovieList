package com.bilgiland.movielist.data.remote

import com.bilgiland.movielist.data.model.MovieResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResultModel>

}