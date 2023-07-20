package com.example.xogame.ui.screen.join_game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.xogame.ui.screen.AppDestination

private val ROUTE = AppDestination.JoinGame.route

fun NavController.navigateToJoinGame(username: String) {
    navigate("$ROUTE/$username")
}

fun NavGraphBuilder.joinGameRoute() {
    composable(route = "$ROUTE/{${JoinGameArgs.USERNAME_ARG}}",
        arguments = listOf(
            navArgument(JoinGameArgs.USERNAME_ARG) { NavType.StringType }
        )) { JoinGameScreen() }
}

class JoinGameArgs(savedStateHandle: SavedStateHandle) {
    val username: String = checkNotNull(savedStateHandle[USERNAME_ARG])

    companion object {
        const val USERNAME_ARG = "username"
    }
}