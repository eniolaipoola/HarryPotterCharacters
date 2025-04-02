package com.tei.harrypottercharacter.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tei.harrypottercharacter.R
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.model.Wand
import com.tei.harrypottercharacter.ui.component.CharacterHouseIcon


@Composable
fun CharacterItemCompose(
    character: CharacterModel,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_shape_large)),
        colors = CardDefaults.cardColors(
            contentColor = colorResource(R.color.white),
            containerColor = colorResource(R.color.white)
        ),
        modifier = modifier
            .height(dimensionResource(R.dimen.card_item_height))
            .fillMaxWidth()
        ,
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.elevation_dimen)),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Character image
            Image(
                painter = rememberAsyncImagePainter(
                    model = character.image,
                    placeholder = painterResource(R.drawable.image_placeholder),
                    error = painterResource(R.drawable.image_placeholder)
                ),
                contentDescription = null,
                modifier = modifier
                    .width(dimensionResource(R.dimen.image_width))
                    .height(dimensionResource(R.dimen.card_item_height))
                    .padding(dimensionResource(R.dimen.padding_small))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            //Character name, actor's name and specie
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            ) {

                CharacterInfoRow(
                    stringResource(R.string.name_placeholder),
                    character.name,
                    stringResource(R.string.name_default_string))
                CharacterInfoRow(
                    stringResource(R.string.actor_s_name_placeholder),
                    character.actor,
                    stringResource(R.string.actor_name_default_text),
                )
                CharacterInfoRow(
                    stringResource(R.string.specie_placeholder),
                    character.specie,
                    stringResource(R.string.actor_name_default_text)
                )
                Spacer(modifier = modifier.height(dimensionResource(R.dimen.spacer_height)))
            }

            //House icon
            Box(
                modifier = modifier
                    .width(dimensionResource(R.dimen.search_bar_height))
                    .height(dimensionResource(R.dimen.search_bar_height)),
                contentAlignment = Alignment.CenterEnd
            ) {
                CharacterHouseIcon(
                    houseColor = character.houseColor
                )
            }
        }

    }
}

@Composable
fun CharacterInfoRow(name: String, value: String?, emptyState: String
) {
    Row(
        modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = name,
            maxLines = 2,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.text_size_medium).value.sp
            ),
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.primary_color)
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacer_height)))
        Text(
            text = value ?: emptyState,
            maxLines = 1,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.text_size_medium).value.sp
            ),
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.primary_color)
        )

    }
}

@Preview
@Composable
fun CharacterItemPreview() {
    val character = CharacterModel(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
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

    CharacterItemCompose(character, modifier = Modifier)

}