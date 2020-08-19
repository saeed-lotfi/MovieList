package com.bilgiland.movielist.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bilgiland.movielist.data.model.MovieModel


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(model: List<MovieModel>)

    @Query("select * from MovieModel")
    fun getAll(): PagingSource<Int, MovieModel>


}