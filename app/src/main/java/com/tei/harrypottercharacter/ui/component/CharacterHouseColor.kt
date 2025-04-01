package com.tei.harrypottercharacter.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tei.harrypottercharacter.R


@Composable
fun CharacterHouseColor(
    houseColor: Color,
    onCallback : () -> Unit
) {
    IconButton(
        onClick = {
            //handle click action
            onCallback()
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_character_house),
            contentDescription = stringResource(id = R.string.house_color_text),
            tint = houseColor
        )
    }
}
