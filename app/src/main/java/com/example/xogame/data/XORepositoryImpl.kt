package com.example.xogame.data

import androidx.datastore.dataStore
import com.example.xogame.data.local.PlayerDatastore

class XORepositoryImpl(private val dataStore: PlayerDatastore) : XORepository {
    override suspend fun savePlayerName(playerName: String) {
        dataStore.savePlayerName(playerName)
    }

    override fun getPlayerName(): String? {
        return dataStore.getPlayerName()
    }

}