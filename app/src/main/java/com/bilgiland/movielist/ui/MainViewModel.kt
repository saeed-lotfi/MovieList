package com.bilgiland.movielist.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bilgiland.movielist.data.ApiService
import com.bilgiland.movielist.data.respository.MovieDatSource

class MainViewModel @ViewModelInject constructor(private val api: ApiService) :
    @androidx.hilt.lifecycle.ViewModelInject ViewModel() {
    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        MovieDatSource(api)
    }.flow
        .cachedIn(viewModelScope)
}