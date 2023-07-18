package com.example.xogame.ui.screen.start_game

data class StartGameUiState(
    val roomId: String = "",
    val isFriendActive: Boolean = false,
    val isLoading:Boolean = true
)
