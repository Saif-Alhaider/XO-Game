package com.example.xogame.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerializedName("x")
    val x: Int? = null,
    @SerializedName("y")
    val y: Int? = null
)