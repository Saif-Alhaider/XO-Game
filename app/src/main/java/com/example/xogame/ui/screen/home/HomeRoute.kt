package com.example.xogame.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "home"

fun NavGraphBuilder.homeRoute() {
    composable(route = ROUTE) { HomeScreen() }
}