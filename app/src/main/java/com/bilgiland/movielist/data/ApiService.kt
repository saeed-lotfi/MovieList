package com.bilgiland.movielist.data

import com.bilgiland.movielist.data.model.MovieResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): MovieResultModel

}