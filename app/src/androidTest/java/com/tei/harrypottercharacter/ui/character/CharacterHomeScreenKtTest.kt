package com.tei.harrypottercharacter.ui.character

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.model.Wand
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.ui.theme.AppTheme
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterHomeScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel: CharacterViewModel = mockk()

    private val characterList = listOf(
        CharacterModel(
            id = "9e3f7ce4-b8a7-4244-b709-dae5c1f1d4a8",
            name = "Harry Potter",
            alternateNames = listOf(),
            specie = "human",
            gender = "male",
            house = "Gryffindor",
            dateOfBirth = "31-07-1980",
            yearOfBirth = 1980,
            wizard = true,
            ancestry = "half-blood",
            eyeColor = "green",
            hairColor = "black",
            wand = Wand(
                wood = "holly",
                core = "phoenix tail feather",
                length = 11.0
            ),
            patronus = "stag",
            hogwartsStudent = true,
            hogwartsStaff = false,
            actor = "Daniel Radcliffe",
            alternateActors = listOf(),
            alive = true,
            image = "https://ik.imagekit.io/hpapi/harry.jpg"
        )
    )

    @Test
    fun testSearchCharacterFunctionality() {
        val stateFlow = MutableStateFlow(NetworkUIState.Success(characterList))

        coEvery { viewModel.getAllCharacters() } returns Unit
        coEvery { viewModel.uiState } returns stateFlow

        coEvery { viewModel.performSearch(any()) } returns Unit

        composeTestRule.setContent {
            AppTheme {
                CharactersHomeScreen(
                    onClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag("search_text_field")
            .performTextInput("Harry")

        composeTestRule.onNodeWithTag("search_text_field")
            .performImeAction()

        verify { viewModel.performSearch("Harry") }

        composeTestRule.onNodeWithText("Harry").assertIsDisplayed()


    }

    @Test
    fun testSearch_WhenResultIsEmpty() {
        val characterList = emptyList<CharacterModel>()
        val stateFlow = MutableStateFlow(NetworkUIState.Success(characterList))

        coEvery { viewModel.getAllCharacters() } returns Unit
        every { viewModel.uiState } returns stateFlow
        coEvery { viewModel.performSearch(any()) } returns Unit

        composeTestRule.setContent {
            AppTheme  {
                CharactersHomeScreen(
                    onClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag("search_text_field")
            .performTextInput("Eniola Ipoola")

        composeTestRule.onNodeWithTag("search_text_field")
            .performImeAction()

        composeTestRule.onNodeWithText("No data available").assertIsDisplayed()
    }

    @Test
    fun testSearchFunctionality_WhenAnErrorOccurred() {
        val stateFlow = MutableStateFlow(NetworkUIState.Error(Throwable("Network Error")))

        coEvery { viewModel.getAllCharacters() } returns Unit
        every { viewModel.uiState } returns stateFlow
        coEvery { viewModel.performSearch(any()) } returns Unit

        composeTestRule.setContent {
            AppTheme  {
                CharactersHomeScreen(
                    onClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag("search_text_field")
            .performTextInput("Eniola Ipoola")

        composeTestRule.onNodeWithTag("search_text_field")
            .performImeAction()

        composeTestRule.onNodeWithText("Network Error").assertIsDisplayed()
    }

    @Test
    fun testFetchCharactersFunctionality() {
        val stateFlow = MutableStateFlow(NetworkUIState.Success(characterList))

        every { viewModel.uiState } returns stateFlow
        coEvery { viewModel.getAllCharacters() } returns Unit
        coEvery { viewModel.performSearch(any()) } returns Unit

        composeTestRule.setContent {
            AppTheme {
                CharactersHomeScreen(
                    onClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag("characters_list").assertExists()

        characterList.forEach { character ->
            composeTestRule.onNodeWithTag("character_item${character.id}")
                .assertIsDisplayed()

            character.name?.let { composeTestRule.onNodeWithText(it).assertIsDisplayed() }

        }
    }

    @Test
    fun testCharacterOnclick_NavigatesToDetailPage() {
        val stateFlow = MutableStateFlow(NetworkUIState.Success(characterList))

        every { viewModel.uiState } returns stateFlow
        coEvery { viewModel.getAllCharacters() } returns Unit
        coEvery { viewModel.performSearch(any()) } returns Unit

        var clickedItem: CharacterModel? = null
        val onClick: (CharacterModel) -> Unit = { character ->
            clickedItem = character
        }

        composeTestRule.setContent {
            AppTheme {
                CharactersHomeScreen(
                    onClick =  onClick ,
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag("character_item${characterList[0].id}")
            .performClick()

        assertEquals(characterList[0], clickedItem)

        characterList[0].name?.let {
            composeTestRule.onNodeWithText(it)
                .assertIsDisplayed()
        }
    }
}

