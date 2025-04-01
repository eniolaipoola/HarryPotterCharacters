package com.tei.harrypottercharacter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.tei.harrypottercharacter.R
import com.tei.harrypottercharacter.ui.component.SearchTopBar

@Composable
fun CharactersHomeScreen(
    paddingValues: PaddingValues
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.primary_color),
                contentColor = colorResource(R.color.white)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
               modifier = Modifier.height(200.dp)
                .background(
                    color = colorResource(R.color.primary_color),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
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
        }

        CharacterScreenContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun CharacterScreenContent(
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = dimensionResource(R.dimen.padding_large))
    ) {

        Spacer(modifier = modifier.height(dimensionResource(R.dimen.padding_medium)))


    }
}