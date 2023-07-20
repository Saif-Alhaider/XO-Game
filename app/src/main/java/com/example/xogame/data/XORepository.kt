package com.example.xogame.data

import com.example.xogame.util.ResponseResult
import kotlinx.coroutines.flow.Flow

interface XORepository {
    suspend fun savePlayerName(playerName: String)
    fun getPlayerName(): String?
    suspend fun newGame(username: String): ResponseResult<String>

    suspend fun joinGame(username: String, roomId: String, ): ResponseResult<Unit>

    suspend fun observeGame(onFriendNotify: () -> Unit = {}): Flow<GameTurn?>

    suspend fun sendXorO(game:Game)

    suspend fun endGame()

}