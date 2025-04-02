package com.tei.harrypottercharacter.ui.component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tei.harrypottercharacter.ui.character.CharacterDetailsPage
import com.tei.harrypottercharacter.ui.character.CharactersHomeScreen
import com.tei.harrypottercharacter.util.CHARACTER_ID

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.CharactersListScreen.route
    ) {
        composable(route = NavScreen.CharactersListScreen.route) {
            CharactersHomeScreen(
                onClick = { characterItem ->
                    navController.navigate("${NavScreen.CharacterDetailsScreen.route}/${characterItem.id}")
                }
            )
        }

        composable(
            route = "${NavScreen.CharacterDetailsScreen.route}/{$CHARACTER_ID}"
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString(CHARACTER_ID) ?: ""
            CharacterDetailsPage(
                characterId = characterId,
                backHandler = { navController.popBackStack() }
            )
        }

    }
}