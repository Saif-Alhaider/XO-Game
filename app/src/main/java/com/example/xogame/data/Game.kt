package com.example.xogame.data

data class Game(
    val x: Int,
    val y: Int
)

fun GameDto.toGame(): Game {
    return Game(
        x = this.x ?: 0,
        y = this.y ?: 0
    )
}