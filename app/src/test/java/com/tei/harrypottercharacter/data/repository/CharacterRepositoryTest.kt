package com.tei.harrypottercharacter.data.repository

import com.tei.harrypottercharacter.data.local.CharactersDAO
import com.tei.harrypottercharacter.data.local.entities.CharacterEntity
import com.tei.harrypottercharacter.data.network.APIService
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.di.DispatcherProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class CharacterRepositoryTest {

    private lateinit var charactersDao: CharactersDAO
    private lateinit var apiService: APIService
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var characterRepository: CharacterRepository


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        Dispatchers.setMain(Dispatchers.Unconfined)

        charactersDao = mockk()
        apiService = mockk()
        dispatcherProvider = mockk()

        characterRepository = CharacterRepository(
            charactersDao, apiService, dispatcherProvider)
    }

    @Test
    fun fetchAllCharacters_FromDatabase() = runTest {
        val localData = characterList
        val expectedDomainData = characterModelList

        coEvery { charactersDao.getAllCharacters() } returns flowOf(localData)
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined

        val result = characterRepository.fetchAllCharacters().toList()

        assertTrue(result.isNotEmpty())
        val successResult = result.last()

        successResult as NetworkUIState.Success
        assertEquals(expectedDomainData.size, successResult.data.size)
        assertEquals(expectedDomainData, successResult.data)

        coVerify { charactersDao.getAllCharacters() }

    }

    @Test
    fun fetchAllCharactersFromDatabase_OnEmptyData_FetchFromNetwork() = runTest {
        val emptyData = emptyList<CharacterEntity>()
        val apiData = characterList

        coEvery { charactersDao.getAllCharacters() } returnsMany
                listOf(flowOf(emptyData), flowOf(apiData))
        coEvery { apiService.fetchCharactersList() } returns apiData
        coEvery { charactersDao.updateAllCharacters(apiData) } returns Unit
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined

        val results = characterRepository.fetchAllCharacters().toList()

        assertTrue(results.first() is NetworkUIState.Loading)
        val successResult = results.last()
        assertTrue(successResult is NetworkUIState.Success)

        successResult as NetworkUIState.Success
        assertEquals(apiData.size, successResult.data.size)

        coVerify { charactersDao.getAllCharacters() }
        coVerify { apiService.fetchCharactersList() }
        coVerify { charactersDao.updateAllCharacters(apiData) }
    }

    @Test
    fun fetchCharacters_NetworkRequestFail() = runTest {
        val emptyList = emptyList<CharacterEntity>()

        coEvery { charactersDao.getAllCharacters() } returns flowOf(emptyList)
        coEvery { apiService.fetchCharactersList() } throws Exception("Network Error")
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined

        val results = characterRepository.fetchAllCharacters().toList()

        assertTrue(results.first() is NetworkUIState.Loading)

        val errorResult = results.last()
        assertTrue(errorResult is NetworkUIState.Error)

        errorResult as NetworkUIState.Error
        assertEquals("Network Error", errorResult.exception.message)

        coVerify { charactersDao.getAllCharacters() }
        coVerify { apiService.fetchCharactersList() }
    }

    @Test
    fun fetchCharacterById_Successful() = runTest {
        val characterId = "9e3f7ce4-b9a7-4244-b709-dea5c1f1d4a8"
        val characterEntity = characterEntity
        val localData = flowOf(characterEntity)

        coEvery { charactersDao.getCharacterById(characterId) } returns localData
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined

        val result = characterRepository.getCharacterById(characterId).first()

        assertEquals(characterId, result.id)
        assertEquals("Hanny Potter", result.name)

        coVerify { charactersDao.getCharacterById(characterId) }
    }

    @Test
    fun searchCharacterFromDatabase_ReturnResult() = runTest {
        val searchQuery = "Harry"
        val localData = characterList
        coEvery { charactersDao.searchCharactersList(searchQuery) } returns flowOf(localData)
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined

        val result = characterRepository.searchCharacter(searchQuery).first()

        assertTrue(result is NetworkUIState.Success)

        val successResult = result as NetworkUIState.Success
        assertEquals(localData.size, successResult.data.size)

        coVerify { charactersDao.searchCharactersList(searchQuery) }
    }

    @Test
    fun fetchAllCharacters_ThrowsError_OnEmptyData() = runTest {
        val emptyList = emptyList<CharacterEntity>()
        val localData = flowOf(emptyList)

        coEvery { charactersDao.getAllCharacters() } returns localData
        coEvery { apiService.fetchCharactersList() } throws RuntimeException("Network error")
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined


        val results = characterRepository.fetchAllCharacters().toList()
        assertTrue(results.first() is NetworkUIState.Loading)

        val errorResult = results.last()
        assertTrue(errorResult is NetworkUIState.Error)

        errorResult as NetworkUIState.Error
        assertEquals("Network error", errorResult.exception.message)

        coVerify { charactersDao.getAllCharacters() }
        coVerify { apiService.fetchCharactersList() }
    }

}