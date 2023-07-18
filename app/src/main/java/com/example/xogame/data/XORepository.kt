package com.example.xogame.data

interface XORepository {
    suspend fun savePlayerName(playerName: String)
    suspend fun getPlayerName(): String?
    suspend fun clearPlayerName()
}