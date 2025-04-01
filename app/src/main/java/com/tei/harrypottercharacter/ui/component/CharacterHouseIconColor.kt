package com.tei.harrypottercharacter.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.tei.harrypottercharacter.R


@Composable
fun CharacterHouseIcon(
    houseColor: Color,
) {
    Icon(
        imageVector = Icons.Filled.CheckCircle,
        contentDescription = stringResource(id = R.string.house_color_text),
        tint = houseColor
    )

}
