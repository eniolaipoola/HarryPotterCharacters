package com.tei.harrypottercharacter.ui.character

import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.data.repository.CharacterRepository
import com.tei.harrypottercharacter.data.repository.characterModel
import com.tei.harrypottercharacter.data.repository.characterModelList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private lateinit var viewModel: CharacterViewModel
    private val repository: CharacterRepository = mockk()


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = CharacterViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getAllCharacters_Success() = runTest {
        val characterList = characterModelList
        val flow = flowOf(NetworkUIState.Success(characterList))
        every { repository.fetchAllCharacters() } returns flow

        viewModel.getAllCharacters()

        advanceUntilIdle()

        assertEquals(NetworkUIState.Success(characterList), viewModel.uiState.value)
    }

    @Test
    fun getAllCharacters_Error() = runTest {
        val exception = Exception("An error occurred")
        every { repository.fetchAllCharacters() } returns flow { throw exception }

        viewModel.getAllCharacters()

        advanceUntilIdle()

        assertEquals(NetworkUIState.Error(exception), viewModel.uiState.value)
    }

    @Test
    fun performSearch_Success() = runTest {
        val searchResults = characterModelList
        val flow = flowOf(NetworkUIState.Success(searchResults))
        every { repository.searchCharacter("Ron") } returns flow

        viewModel.performSearch("Ron")

        advanceUntilIdle()

        assertEquals(NetworkUIState.Success(searchResults), viewModel.uiState.value)
    }

    @Test
    fun performSearch_Error() = runTest {
        val exception = Exception("An error occurred")
        every { repository.searchCharacter(any()) } returns flow { throw exception }

        viewModel.performSearch("Potter")

        advanceUntilIdle()

        assertEquals(NetworkUIState.Error(exception), viewModel.uiState.value)
    }

    @Test
    fun fetchCharacterById_Success() = runTest {
        val characterItem = characterModel
        val flow = flowOf(characterItem)
        every { repository.getCharacterById("9e3f7ce4-b9a7-4244-b709-dea5c1f1d4a8") } returns flow

        viewModel.fetchCharacterById("9e3f7ce4-b9a7-4244-b709-dea5c1f1d4a8")

        advanceUntilIdle()

        assertEquals(characterItem, viewModel.character.value)
    }

    @Test
    fun `onCleared cancels fetchJob`() {
        val job: Job = mockk(relaxed = true)
        viewModel.fetchJob = job

        viewModel.onCleared()

        verify { job.cancel() }
    }




}