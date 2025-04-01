package com.tei.harrypottercharacter.data.repository

import com.tei.harrypottercharacter.data.local.CharactersDAO
import com.tei.harrypottercharacter.data.local.entities.toDomainModel
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.network.APIService
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.di.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val charactersDao: CharactersDAO,
    private val apiService: APIService,
    private val dispatcherProvider: DispatcherProvider
) {

    fun fetchAllCharacters() : Flow<NetworkUIState<List<CharacterModel>>> = flow {
        try {
            emit(NetworkUIState.Loading)

            //fetch character data from database
            val savedCharacters = charactersDao.getAllCharacters().firstOrNull()
            if(!savedCharacters.isNullOrEmpty()) {
                val characterData = savedCharacters.map { it.toDomainModel()}
                emit(NetworkUIState.Success(characterData))
            }

            //if database is empty, fetch from API call
            try {
                val response = apiService.fetchCharactersList()
                charactersDao.updateAllCharacters(response)

                //re-fetch data from database again, just in case
                val updatedCharacterList = charactersDao.getAllCharacters().firstOrNull()
                if(!updatedCharacterList.isNullOrEmpty()) {
                    val characterList = updatedCharacterList.map { it.toDomainModel()}
                    emit(NetworkUIState.Success(characterList))
                }
            } catch(exception: Exception) {
                emit(NetworkUIState.Error(exception))
            }

        } catch (e: Exception) {
            emit(NetworkUIState.Error(e))
        }

    }.flowOn(dispatcherProvider.io)

}