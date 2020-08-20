package com.bilgiland.movielist.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromCountryLangList(value: List<Int>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Int> {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
}