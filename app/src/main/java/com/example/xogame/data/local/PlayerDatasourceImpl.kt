package com.example.xogame.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayerDatasourceImpl @Inject constructor(context: Context) : PlayerDatasource {

    companion object {
        private const val PREFERENCES_FILE_NAME = "xo_game"
        private val PLAYER_KEY = stringPreferencesKey("playerName")
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCES_FILE_NAME
    )
    private val prefDataStore = context.preferencesDataStore

    override suspend fun savePlayerName(playerName: String) {
        prefDataStore.edit { preferences ->
            preferences[PLAYER_KEY] = playerName
        }
    }

    override suspend fun getPlayerName(): String? {
        return prefDataStore.data.map { preferences -> preferences[PLAYER_KEY] }.first()
    }

    override suspend fun clearPlayerName() {
        prefDataStore.edit { preferences ->
            preferences.remove(PLAYER_KEY)
        }
    }
}