package com.example.xogame.ui.screen.play

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.xogame.ui.screen.AppDestination

val ROUTE = AppDestination.BoardGame.route


fun NavController.navigateToPlay(character: String,secondPlayerName : String) {
    navigate("$ROUTE/$character/$secondPlayerName")
}

fun NavGraphBuilder.playRoute(navController: NavController) {
    composable(route = "$ROUTE/{${PlayArgs.CHARACTER_ARG}}/{${PlayArgs.SECOND_PLAYER_NAME}}", arguments = listOf(
        navArgument(PlayArgs.CHARACTER_ARG) { NavType.StringType },
        navArgument(PlayArgs.SECOND_PLAYER_NAME) { NavType.StringType },
    )) { PlayGameScreen() }
}

class PlayArgs(savedStateHandle: SavedStateHandle) {
    val character: String = checkNotNull(savedStateHandle[CHARACTER_ARG])
    val secondPlayerName: String = checkNotNull(savedStateHandle[SECOND_PLAYER_NAME])

    companion object {
        const val CHARACTER_ARG = "character"
        const val SECOND_PLAYER_NAME = "secondPlayerName"
    }
}