package com.tei.harrypottercharacter.ui.component.navigation

sealed class NavScreen(val route: String) {
    data object CharactersListScreen : NavScreen("HomeScreen")
    data object CharacterDetailsScreen : NavScreen("DetailsScreen")
}