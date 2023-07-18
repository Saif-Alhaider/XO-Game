package com.example.xogame.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerializedName("position")
    val position: Position?=null,
    @SerializedName("playTurn")
    val playTurn: String?=null
) {
    @Serializable
    data class Position(
        @SerializedName("row")
        val row: Int?=null,
        @SerializedName("column")
        val column: Int?=  null
    )
}