package com.tei.harrypottercharacter.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}