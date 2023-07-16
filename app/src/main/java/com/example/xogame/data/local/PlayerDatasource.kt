package com.example.xogame.data.local

interface PlayerDatasource {
    suspend fun savePlayerName(playerName: String)
    suspend fun getPlayerName(): String?
    suspend fun clearPlayerName()
}