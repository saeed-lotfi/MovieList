package com.bilgiland.movielist.ui.detail

import androidx.lifecycle.*
import com.bilgiland.movielist.data.model.MovieDetail
import com.bilgiland.movielist.data.respository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieDetailRepository):
    ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun getDetail(movieId: Long): LiveData<MovieDetail> =
        repository.getMovieDetail(movieId)
            .catch { _error.postValue(true) }
            .asLiveData()


}