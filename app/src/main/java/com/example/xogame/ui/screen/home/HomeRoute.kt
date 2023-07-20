package com.example.xogame.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.xogame.ui.screen.AppDestination

private val ROUTE = AppDestination.Home.route

fun NavGraphBuilder.homeRoute() {
    composable(route = ROUTE) { HomeScreen() }
}

fun NavController.navigateToHome() {
    navigate(ROUTE)
}