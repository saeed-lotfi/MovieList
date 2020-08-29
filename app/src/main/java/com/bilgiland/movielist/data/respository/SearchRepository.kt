package com.bilgiland.movielist.data.respository

import com.bilgiland.movielist.data.remote.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiService) {

    fun getMovieSearch(searchQuery: String) = SearchMovieDatSource(api, searchQuery)


}