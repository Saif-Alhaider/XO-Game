package com.example.xogame.ui.screen.play

import androidx.compose.ui.graphics.Color

data class PlayUiState(
    val board: List<List<XOCard>> = listOf(
        listOf(XOCard(), XOCard(), XOCard()),
        listOf(XOCard(), XOCard(), XOCard()),
        listOf(XOCard(), XOCard(), XOCard())
    ),
    val currentPlayer: String = "X",
    val winner: String = "",
    val isActive: Boolean = currentPlayer == "X",
    val player2Name : String = ""
) {
    data class XOCard(
        val value: String = "",
        val color: Color = Color.Transparent
    )
}
