package com.bilgiland.movielist.data.respository

import com.bilgiland.movielist.ConstValue
import com.bilgiland.movielist.ConstValue.API_KEY
import com.bilgiland.movielist.data.model.MovieDetail
import com.bilgiland.movielist.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val api:ApiService){

fun getMovieDetail(movieId:Long)= flow{

        emit(api.getMovie(movieId,API_KEY))

    }.flowOn(Dispatchers.IO)


}