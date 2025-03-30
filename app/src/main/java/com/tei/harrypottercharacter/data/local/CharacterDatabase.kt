package com.tei.harrypottercharacter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tei.harrypottercharacter.data.local.converter.StringListConverter
import com.tei.harrypottercharacter.data.local.converter.WandConverter
import com.tei.harrypottercharacter.data.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(WandConverter::class, StringListConverter::class)
abstract class CharacterDatabase : RoomDatabase() {

}