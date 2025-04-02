package com.tei.harrypottercharacter.ui.component.navigation

sealed class NavScreen(val route: String) {
    object CharactersListScreen : NavScreen("HomeScreen")
    object CharacterDetailsScreen : NavScreen("DetailsScreen")
}