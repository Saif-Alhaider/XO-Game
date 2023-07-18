package com.example.xogame.ui.screen.play

data class PlayUiState(
    val board: List<List<String>> = listOf(
        listOf("", "", ""),
        listOf("", "", ""),
        listOf("", "", "")
    ),
    val turn: String = "X",
    val winner: String = "",
    val isActive:Boolean = true
)
