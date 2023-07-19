package com.example.xogame.ui.screen.play

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE = "board game"


fun NavController.navigateToPlay(character: String) {
    navigate("$ROUTE/$character"){
        this.popUpToRoute
    }
}

fun NavGraphBuilder.playRoute(navController: NavController) {
    composable(route = "$ROUTE/{${PlayArgs.CHARACTER_ARG}}", arguments = listOf(
        navArgument(PlayArgs.CHARACTER_ARG) { NavType.StringType }
    )) { PlayGameScreen() }
}

class PlayArgs(savedStateHandle: SavedStateHandle) {
    val character: String = checkNotNull(savedStateHandle[CHARACTER_ARG])

    companion object {
        const val CHARACTER_ARG = "character"
    }
}