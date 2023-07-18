package com.example.xogame.data


import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("position")
    val position: Position?,
    @SerializedName("playTurn")
    val playTurn: String?
) {
    data class Position(
        @SerializedName("row")
        val row: Int?,
        @SerializedName("column")
        val column: Int?
    )
}