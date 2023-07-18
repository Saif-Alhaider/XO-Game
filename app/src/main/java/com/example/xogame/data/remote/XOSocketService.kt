package com.example.xogame.data.remote

import com.example.xogame.data.Game
import com.example.xogame.util.ResponseResult
import kotlinx.coroutines.flow.Flow


interface XOSocketService {
    suspend fun initSession(
        username: String
    ): ResponseResult<String>

    suspend fun joinSession(
        username: String,
        roomId: String,
    ): ResponseResult<Unit>

    suspend fun observeGame(onFriendNotify: () -> Unit = {}): Flow<Game>

    suspend fun sendXO(game:Game)

    suspend fun closeSession()
}