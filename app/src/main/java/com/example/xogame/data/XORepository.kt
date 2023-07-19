package com.example.xogame.data

interface XORepository {
    suspend fun savePlayerName(playerName: String)
    fun getPlayerName(): String?
}