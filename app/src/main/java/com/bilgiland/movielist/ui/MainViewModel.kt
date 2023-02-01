package com.bilgiland.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bilgiland.movielist.data.remote.ApiService
import com.bilgiland.movielist.data.respository.MovieDatSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private var pagingSource: MovieDatSource? = null

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        MovieDatSource(api).also {
            pagingSource = it
        }
    }.flow
        .cachedIn(viewModelScope)


    fun clearData() =
        pagingSource?.invalidate()
}