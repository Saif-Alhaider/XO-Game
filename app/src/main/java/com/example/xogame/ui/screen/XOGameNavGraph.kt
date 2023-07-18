package com.example.xogame.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xogame.ui.screen.home.homeRoute
import com.example.xogame.ui.screen.join_game.joinGameRoute
import com.example.xogame.ui.screen.play.PlayGameScreen
import com.example.xogame.ui.screen.start_game.startGameRoute

@Composable
fun XOGameNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = AppDestination.BoardGame.route) {
        homeRoute(navHostController)
        startGameRoute(navHostController)
        joinGameRoute(navHostController)
        composable(route = AppDestination.BoardGame.route) { PlayGameScreen()}
    }
}