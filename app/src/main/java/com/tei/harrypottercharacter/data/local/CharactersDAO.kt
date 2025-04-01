package com.tei.harrypottercharacter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tei.harrypottercharacter.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDAO {

/*    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characterList: List<CharacterEntity>)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllCharacters(charactersList: List<CharacterEntity>)

    @Query("Select * From characters")
    fun getAllCharacters() : Flow<List<CharacterEntity>>

    @Query("Delete from characters")
    suspend fun deleteAllCharacters()



}