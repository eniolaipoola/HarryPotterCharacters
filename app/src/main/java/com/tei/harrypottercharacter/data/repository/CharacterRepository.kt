package com.tei.harrypottercharacter.data.repository

import com.tei.harrypottercharacter.data.local.CharactersDAO
import com.tei.harrypottercharacter.data.local.entities.toDomainModel
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.network.APIService
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.di.DispatcherProvider
import kotlinx.coroutines.flow.Flow
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
            val response = apiService.fetchCharactersList()
            charactersDao.updateAllCharacters(response)
            val characterResponse = response.map { it.toDomainModel()}
            emit(NetworkUIState.Success(characterResponse))
        } catch (e: Exception) {
            emit(NetworkUIState.Error(e))
        }

    }.flowOn(dispatcherProvider.io)

}