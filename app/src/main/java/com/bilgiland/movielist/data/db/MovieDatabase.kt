package com.bilgiland.movielist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bilgiland.movielist.data.model.MovieModel


@Database(entities = [MovieModel::class], version = 1,exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}