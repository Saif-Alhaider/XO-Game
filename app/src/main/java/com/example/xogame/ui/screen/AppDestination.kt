package com.example.xogame.ui.screen

sealed class AppDestination(val route: String) {
    object Home : AppDestination(route = "home")
    object StartGame : AppDestination(route = "start game")
    object JoinGame : AppDestination(route = "join game")
}