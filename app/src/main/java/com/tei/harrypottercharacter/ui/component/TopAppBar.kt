package com.tei.harrypottercharacter.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tei.harrypottercharacter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String = stringResource(R.string.app_title),
    navigationIcon: ImageVector,
    navigationIconClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.background_color_transparent),
            titleContentColor = colorResource(R.color.primary_color)
        ),
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked()
            }) {
                Icon(
                    //imageVector = Icons.Filled.Menu,
                    imageVector = navigationIcon,
                    contentDescription = stringResource(R.string.menu_icon)
                )
            }
        },
        title = {
            Text(
                text = title ,
                style = TextStyle(
                    fontSize = dimensionResource(R.dimen.text_size_large).value.sp
                ),
                textAlign = TextAlign.Start
            )
        }
    )

}