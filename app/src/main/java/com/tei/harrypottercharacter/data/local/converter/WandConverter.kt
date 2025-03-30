package com.tei.harrypottercharacter.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tei.harrypottercharacter.data.model.Wand

class WandConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWand(wand: Wand): String {
        return gson.toJson(wand)
    }

    @TypeConverter
    fun toWand(wandString: String): Wand {
        val type = object : TypeToken<Wand>() {}.type
        return gson.fromJson(wandString, type)
    }
}