package com.tei.harrypottercharacter.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tei.harrypottercharacter.data.local.entities.CharacterEntity

@Dao
interface CharactersDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characterList: List<CharacterEntity>)

    @Transaction
    suspend fun updateAllCharacters(charactersList: List<CharacterEntity>) {
        deleteAllCharacters()
        insertCharacters(charactersList)
    }

    @Update
    suspend fun update(characterItem: CharacterEntity)

    @Delete
    suspend fun deleteOneCharacter(characterItem: CharacterEntity)

    @Query("Delete from characters")
    suspend fun deleteAllCharacters()



}