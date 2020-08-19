package com.bilgiland.movielist.data.respository

import androidx.paging.PagingSource
import com.bilgiland.movielist.ConstValue.API_KEY
import com.bilgiland.movielist.data.ApiService
import com.bilgiland.movielist.data.model.MovieModel
import kotlinx.coroutines.currentCoroutineContext
import javax.inject.Inject

class MovieDatSource @Inject constructor(private val api: ApiService) :
    PagingSource<Int, MovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        try {
            val nextPageNumber = params.key ?: 1
            val reponse = api.getPosts(nextPageNumber, API_KEY)

            return LoadResult.Page(
                data = reponse.movieModels,
                prevKey = null,
                nextKey = nextPageNumber.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}