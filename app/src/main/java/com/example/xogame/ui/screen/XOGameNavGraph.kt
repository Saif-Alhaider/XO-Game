package com.example.xogame.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.xogame.ui.screen.home.homeRoute
import com.example.xogame.ui.screen.join_game.joinGameRoute
import com.example.xogame.ui.screen.start_game.startGameRoute

@Composable
fun XOGameNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = AppDestination.Home.route) {
        homeRoute(navHostController)
        startGameRoute(navHostController)
        joinGameRoute(navHostController)
    }
}