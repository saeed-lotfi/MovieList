package com.bilgiland.movielist.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bilgiland.movielist.data.model.MovieDetail
import com.bilgiland.movielist.data.respository.MovieDetailRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class MovieDetailViewModel @ViewModelInject constructor(private val repository: MovieDetailRepository):
    ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun getDetail(movieId: Long): LiveData<MovieDetail> =
        repository.getMovieDetail(movieId)
            .catch { _error.postValue(true) }
            .asLiveData()


}