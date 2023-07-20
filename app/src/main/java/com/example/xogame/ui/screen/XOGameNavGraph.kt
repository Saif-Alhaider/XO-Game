package com.example.xogame.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xogame.ui.screen.home.homeRoute
import com.example.xogame.ui.screen.join_game.joinGameRoute
import com.example.xogame.ui.screen.play.PlayGameScreen
import com.example.xogame.ui.screen.play.playRoute
import com.example.xogame.ui.screen.start_game.startGameRoute
import com.example.xogame.ui.theme.XONavigationProvider

@Composable
fun XOGameNavGraph() {
    CompositionLocalProvider(XONavigationProvider provides rememberNavController()) {
        NavHost(navController = XONavigationProvider.current, startDestination = AppDestination.Home.route) {
            homeRoute()
            startGameRoute()
            joinGameRoute()
            playRoute()
        }
    }
}