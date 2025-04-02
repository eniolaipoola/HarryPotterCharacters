package com.tei.harrypottercharacter.data.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tei.harrypottercharacter.data.local.CharactersDAO
import com.tei.harrypottercharacter.data.network.APIService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterRepositoryTest {

    /*@get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CharacterRepository
    private lateinit var charactersDAO: CharactersDAO
    private lateinit var apiService: APIService
    private lateinit var dispatchers: UnconfirmedTestDiapatcher

    @Before
    fun setUp() {
        charactersDAO = mockk(CharactersDAO::class.java)
        apiService = mockk(APIService::class.java)

    }*/

    @After
    fun tearDown() {
    }

    @Test
    fun fetchAllCharacters() {
    }
}