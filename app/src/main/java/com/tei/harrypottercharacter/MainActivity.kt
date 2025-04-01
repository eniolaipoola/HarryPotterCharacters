package com.tei.harrypottercharacter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tei.harrypottercharacter.ui.character.CharactersHomeScreen
import com.tei.harrypottercharacter.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = colorResource(R.color.white),
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.white),
                                titleContentColor = colorResource(R.color.primary_color)
                            ),
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = stringResource(R.string.menu_icon)
                                    )
                                }
                            },
                            title = {
                                Text(
                                    text = stringResource(R.string.app_title),
                                    style = TextStyle(
                                        fontSize = dimensionResource(R.dimen.text_size_large).value.sp
                                    ),
                                    textAlign = TextAlign.Start
                                )
                            },
                            actions = {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = stringResource(R.string.search_text),
                                    tint = colorResource(R.color.primary_color),
                                    modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium))
                                )
                            }
                        )
                    }
                ) {
                    CharactersHomeScreen(it)
                }
            }
        }
    }
}