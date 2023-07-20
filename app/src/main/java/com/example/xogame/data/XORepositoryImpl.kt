package com.example.xogame.data

import com.example.xogame.data.local.PlayerDatastore
import com.example.xogame.data.remote.XOSocketService
import com.example.xogame.util.ResponseResult
import kotlinx.coroutines.flow.Flow

class XORepositoryImpl(
    private val dataStore: PlayerDatastore,
    private val xoSocketService: XOSocketService,
) : XORepository {
    override suspend fun savePlayerName(playerName: String) {
        dataStore.savePlayerName(playerName)
    }

    override fun getPlayerName(): String? {
        return dataStore.getPlayerName()
    }

    override suspend fun newGame(username: String): ResponseResult<String> {
        return xoSocketService.initSession(username)
    }

    override suspend fun joinGame(username: String, roomId: String): ResponseResult<Unit> {
        return xoSocketService.joinSession(username, roomId)
    }

    override suspend fun observeGame(onFriendNotify: (String) -> Unit): Flow<GameTurn?> {
        return xoSocketService.observeGame(onFriendNotify)
    }

    override suspend fun sendXorO(game: Game) {
        return xoSocketService.sendXO(game)
    }

    override suspend fun endGame() {
        return xoSocketService.closeSession()
    }

}