package com.tei.harrypottercharacter.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tei.harrypottercharacter.R
import com.tei.harrypottercharacter.ui.component.TopAppBar
import com.tei.harrypottercharacter.util.formatDateOfBirth
import com.tei.harrypottercharacter.util.getCharacterStatus


@Composable
fun CharacterDetailsPage(
    backHandler: () -> Unit,
    characterId: String?,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val character by viewModel.character.collectAsState()

    LaunchedEffect(key1 = Unit) {
        characterId?.let { viewModel.fetchCharacterById(it) }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_color_transparent)),
        contentColor = colorResource(R.color.background_color_transparent),
        topBar = {
            TopAppBar(
                title = stringResource(R.string.character_detail_page),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconClicked = {
                    backHandler()
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (character != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.padding_large)),
                    elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.elevation_dimen)),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = character?.image,
                            placeholder = painterResource(R.drawable.image_placeholder),
                            error = painterResource(R.drawable.image_placeholder)
                        ),
                        contentDescription = stringResource(R.string.character_image),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            CharacterInfoRow(
                stringResource(R.string.name_placeholder),
                character?.name,
                stringResource(R.string.name_default_string)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            val dateOfBirth = character?.dateOfBirth?.let { formatDateOfBirth(it) }
            CharacterInfoRow(
                stringResource(R.string.date_of_birth),
                dateOfBirth ?: stringResource(R.string.unknown_text),
                stringResource(R.string.name_default_string)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            val aliveStatus = getCharacterStatus(character?.alive)
            CharacterInfoRow(
                stringResource(R.string.is_alive_placeholder),
                aliveStatus ?: stringResource(R.string.unknown_text),
                stringResource(R.string.name_default_string)
            )

        }
    }
}