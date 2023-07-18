package com.example.xogame.ui.screen.start_game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "start game"

fun NavController.navigateToStartGame(name: String) {
    navigate("$ROUTE/$name")
}

fun NavGraphBuilder.startGameRoute(navController: NavController) {
    composable(route = "$ROUTE/{${StartGameArgs.NAME_ARG}}",
        arguments = listOf(
            navArgument("name") { NavType.StringType }
        )) { StartGameScreen(navController = navController) }
}

class StartGameArgs(savedStateHandle: SavedStateHandle) {
    val name: String = checkNotNull(savedStateHandle[NAME_ARG])

    companion object {
        const val NAME_ARG = "name"
    }
}