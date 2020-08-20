package com.bilgiland.movielist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bilgiland.movielist.data.model.MovieModel


@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}