package com.tei.harrypottercharacter.ui.character

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tei.harrypottercharacter.R
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.ui.component.CircularLoader
import com.tei.harrypottercharacter.ui.component.SearchTopBar

@Composable
fun CharactersHomeScreen(
    paddingValues: PaddingValues,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCharacters()
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .height(dimensionResource(R.dimen.fixed_box_height))
                .background(
                    color = colorResource(R.color.primary_color)
                ),
            contentAlignment = Alignment.Center
        ) {
            SearchTopBar(
                searchText = searchText,
                onSearchTextChanged = { searchText = it },
                onSearch = {
                    //perform search action
                }
            )
        }

        when(val state = uiState) {
            is NetworkUIState.Loading -> {
                CircularLoader()
            }
            is NetworkUIState.Success -> {
                val list = state.data
                Log.d("tag", "list size is " + list.size)
                LazyColumn (
                    contentPadding = PaddingValues(dimensionResource(R.dimen.padding_large)),
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
                    horizontalAlignment = Alignment.Start
                ) {
                    list.let { list ->
                        items(list.size) { index ->
                            CharacterItemCompose(character = list[index], modifier = Modifier)
                        }
                    }
                }

                Toast.makeText(context, stringResource(R.string.success_message), Toast.LENGTH_SHORT).show()
            }
            is NetworkUIState.Error -> {
                val error = state.exception.message ?: stringResource(R.string.unknown_error_text)
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}

