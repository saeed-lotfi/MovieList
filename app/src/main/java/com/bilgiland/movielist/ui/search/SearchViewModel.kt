package com.bilgiland.movielist.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bilgiland.movielist.data.model.MovieModel
import com.bilgiland.movielist.data.respository.SearchMovieDatSource
import com.bilgiland.movielist.data.respository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var pagingSource: SearchMovieDatSource? = null

    fun searchMovie(searchQuery: String): Flow<PagingData<MovieModel>> {
        return Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 20)
        ) {
            searchRepository.getMovieSearch(searchQuery).also {
                pagingSource = it
            }
        }.flow
            .cachedIn(viewModelScope)
    }


    fun clearData() =
        pagingSource?.invalidate()
}