package com.example.xogame.data.local

interface PlayerDatastore {
    suspend fun savePlayerName(playerName: String)
    fun getPlayerName(): String?
}