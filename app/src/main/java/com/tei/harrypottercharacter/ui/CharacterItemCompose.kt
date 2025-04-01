package com.tei.harrypottercharacter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tei.harrypottercharacter.R
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.model.Wand
import com.tei.harrypottercharacter.ui.component.CharacterHouseColor


@Composable
fun CharacterItem(
    character: CharacterModel,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_shape_large)),
        colors = CardDefaults.cardColors(
            contentColor = colorResource(R.color.white)
        ),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.elevation_dimen)),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Character image
            Image(
                painter = rememberAsyncImagePainter(
                    model = character.image,
                    placeholder = painterResource(R.drawable.image_placeholder),
                    error = painterResource(R.drawable.icon_error)
                ),
                contentDescription = null,
                modifier = modifier
                    .width(80.dp)
                    .height(100.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            //Character information
            Column(modifier = modifier
                .weight(1f)
                .padding(start = 8.dp).align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                // Character name, actors name and specie
                Text(
                    text = character.name ?: "Harry Potter",
                    maxLines = 1,
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontSize = dimensionResource(R.dimen.text_size_large).value.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(id = R.color.primary_color)
                )

                Spacer(modifier = modifier.height(5.dp))

                Text(
                    text = character.actor ?: "Daniel Radcliffe",
                    style = TextStyle(
                        fontSize = dimensionResource(R.dimen.text_size_large).value.sp
                    ),
                    color = colorResource(R.color.primary_800)
                )

                Spacer(modifier = modifier.height(5.dp))

                HorizontalDivider(
                    thickness = 1.dp,
                    color = colorResource(R.color.primary_color),
                    modifier = modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .height(2.dp)
                )

                Spacer(modifier = modifier.height(5.dp))

                //color icon
                Box(
                    modifier = modifier
                        .width(40.dp)
                        .height(40.dp)
                ) {
                    CharacterHouseColor(
                        onCallback = {

                        },
                        houseColor = character.houseColor
                    )
                }
            }
        }
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
            length = 11
        ),
        patronus = "stag",
        hogwartsStudent = true,
        hogwartsStaff = false,
        actor = "Daniel Radcliffe",
        alternateActors = listOf(),
        alive = true,
        image = "https://ik.imagekit.io/hpapi/harry.jpg"
    )

    CharacterItem(character, modifier = Modifier)

}