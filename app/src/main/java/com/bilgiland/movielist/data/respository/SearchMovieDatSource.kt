package com.bilgiland.movielist.data.respository

import androidx.paging.PagingSource
import com.bilgiland.movielist.ConstValue.API_KEY
import com.bilgiland.movielist.data.model.MovieModel
import com.bilgiland.movielist.data.remote.ApiService
import javax.inject.Inject

class SearchMovieDatSource constructor(
    private val api: ApiService,
    private val searchQuery: String
) :
    PagingSource<Int, MovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getMovieSearch(searchQuery, API_KEY, nextPageNumber)
            val responseData = mutableListOf<MovieModel>()
            val data = response.movieModels ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextPageNumber.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
