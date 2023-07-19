package com.example.xogame.data

data class Game(
    val row: Int,
    val column: Int
)
data class GameTurn(
    val row: Int,
    val column: Int,
    val playTurn: String
)
fun GameDto.asGame(): GameTurn {
    return GameTurn(
        row = position?.row ?: 0,
        column = position?.column ?: 0,
        playTurn = playTurn ?: ""
    )
}

fun GameDto.toGame(): Game {
    return Game(
        row = position?.row ?: 0,
        column = position?.column ?: 0,
    )
}