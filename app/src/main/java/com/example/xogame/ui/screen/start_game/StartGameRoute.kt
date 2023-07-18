package com.example.xogame.ui.screen.start_game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "start game"

fun NavController.navigateToStartGame(username: String) {
    navigate("$ROUTE/$username")
}

fun NavGraphBuilder.startGameRoute(navController: NavController) {
    composable(route = "$ROUTE/{${StartGameArgs.USERNAME_ARG}}",
        arguments = listOf(
            navArgument(StartGameArgs.USERNAME_ARG) { NavType.StringType }
        )) { StartGameScreen(navController = navController) }
}

class StartGameArgs(savedStateHandle: SavedStateHandle) {
    val username: String = checkNotNull(savedStateHandle[USERNAME_ARG])

    companion object {
        const val USERNAME_ARG = "username"
    }
}