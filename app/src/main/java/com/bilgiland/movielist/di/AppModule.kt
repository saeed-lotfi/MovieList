package com.bilgiland.movielist.di

import android.content.Context
import androidx.room.Room
import com.bilgiland.movielist.ConstValue.BASE_URL
import com.bilgiland.movielist.ConstValue.DATABASE_NAME
import com.bilgiland.movielist.data.ApiService
import com.bilgiland.movielist.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRetrofit() =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun getDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun getMovieDao(db: MovieDatabase) = db.movieDao()

}