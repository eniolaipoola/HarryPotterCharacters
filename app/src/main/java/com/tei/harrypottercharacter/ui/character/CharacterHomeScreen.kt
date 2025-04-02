package com.tei.harrypottercharacter.ui.character

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tei.harrypottercharacter.R
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.ui.component.CircularLoader
import com.tei.harrypottercharacter.ui.component.SearchTopBar
import com.tei.harrypottercharacter.ui.component.TopAppBar

@Composable
fun CharactersHomeScreen(
    onClick : (CharacterModel) -> Unit,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCharacters()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = colorResource(R.color.background_color_transparent),
        topBar = {
            TopAppBar(
                title = stringResource(R.string.app_title),
                navigationIcon = Icons.Filled.Menu,
                navigationIconClicked = {}
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
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
                        viewModel.performSearch(searchText)
                    }
                )
            }

            when(val state = uiState) {
                is NetworkUIState.Loading -> {
                    CircularLoader()
                }
                is NetworkUIState.Success -> {
                    val list = state.data
                    if(list.isEmpty()) {
                        Text(
                            text = stringResource(R.string.no_data_found),
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = dimensionResource(R.dimen.text_size_medium).value.sp
                            ),
                            overflow = TextOverflow.Ellipsis,
                            color = colorResource(id = R.color.primary_color)
                        )
                    } else {
                        LazyColumn (
                            contentPadding = PaddingValues(dimensionResource(R.dimen.padding_large)),
                            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
                            horizontalAlignment = Alignment.Start
                        ) {
                            list.let { list ->
                                items(list.size) { index ->
                                    CharacterItemCompose(
                                        character = list[index],
                                        modifier = Modifier,
                                        onClick = {
                                            onClick(list[index])
                                        }
                                    )
                                }
                            }
                        }

                    }
                }

                is NetworkUIState.Error -> {
                    val error = state.exception.message ?: stringResource(R.string.unknown_error_text)
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}

