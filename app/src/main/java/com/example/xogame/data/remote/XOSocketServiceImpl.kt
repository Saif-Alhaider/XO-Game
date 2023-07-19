package com.example.xogame.data.remote

import android.util.Log
import com.example.xogame.data.Game
import com.example.xogame.data.GameDto
import com.example.xogame.data.GameTurn
import com.example.xogame.data.asGame
import com.example.xogame.data.toGame
import com.example.xogame.util.ResponseResult
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class XOSocketServiceImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson
) :
    XOSocketService {
    private var socket: WebSocketSession? = null

    override suspend fun initSession(username: String): ResponseResult<String> {
        return try {
            socket = client.webSocketSession {
                url("$BASE_URL/$username")
            }
            if (socket?.isActive == true) {
                val roomId = (socket?.incoming?.receive() as Frame.Text).readText()
                Log.i(
                    "gg",
                    "initSession: connection was success\n\"$BASE_URL/$username\"\n $roomId"
                )
                ResponseResult.Success(roomId)
            } else {
                ResponseResult.Error("Couldn't Establish Connection")
            }

        } catch (e: Exception) {
            Log.i("gg", "initSession: couldn't $e")
            e.printStackTrace()
            ResponseResult.Error(error = e.localizedMessage ?: "unknown error")
        }
    }

    override suspend fun joinSession(username: String, roomId: String): ResponseResult<Unit> {
        return try {
//            if (socket != null) {
//                socket = null
//            }
            socket = client.webSocketSession { url("$BASE_URL/$username/$roomId") }
            if (socket?.isActive == true) {
                Log.i(
                    "gg",
                    "joinSession: connected to $BASE_URL/$username/$roomId"
                )
                ResponseResult.Success(Unit)
            } else {
                Log.i("gg", "joinSession:Couldn't Establish Connection ")
                ResponseResult.Error("Couldn't Establish Connection")
            }

        } catch (e: Exception) {
            Log.i("gg", "joinSession:$e ")
            ResponseResult.Error(error = e.localizedMessage ?: "unknown error")
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun observeGame(onFriendNotify: () -> Unit): Flow<GameTurn?> {
        Log.i("gg", "observeGame: working")
        return try {
            socket?.incoming?.receiveAsFlow()?.map {
                val value:GameTurn? = when (val response = (it as? Frame.Text)?.readText() ?: "") {
                    "Your Friend Joined the game" -> {
                        onFriendNotify()
                        null
                    }
                    "Not your turn" -> {
                        Log.e("gg", "Not your turn: $response")
                        null
                    }
                    else -> {
                        try {
                            val gameDto = Json.decodeFromString<GameDto>(response)
                            Log.i("gg", "gameDto: $gameDto")
                            gameDto.asGame()
                        }catch (e: Exception){
                            Log.i("gg", "${e.message}")
                            null
                        }

                    }
                }
                value
            } ?: flow {
                Log.i("gg", "observeGame: empty flow \n${socket}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("gg", "observeGame: something went wrong")
            flow { }
        }
    }

    override suspend fun sendXO(game: Game) {
        Log.i("gg", "sendXO: sending: $game")
        try {
            val gameRequest = gson.toJson(game)
            socket?.send(Frame.Text(gameRequest))
            Log.i("gg", "sendXO: $gameRequest")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }

    companion object {
        const val BASE_URL = "ws://xo-moon-cake-hc9jd.ondigitalocean.app/xo-game"
    }
}