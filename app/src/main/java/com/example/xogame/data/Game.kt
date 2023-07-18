package com.example.xogame.data

data class Game(
    val row: Int,
    val column: Int
)

fun GameDto.toGame(): Game {
    return Game(
        row = position?.row ?: 0,
        column = position?.column ?: 0
    )
}