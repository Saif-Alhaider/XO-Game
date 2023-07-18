package com.example.xogame.ui.screen.start_game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.remote.XOSocketService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartGameViewModel @Inject constructor(
    private val xoSocketService: XOSocketService,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _state = MutableStateFlow(StartGameUiState())
    val state = _state.asStateFlow()
    private val args = StartGameArgs(savedStateHandle)

    init {
        createGameSession()
    }

    private fun createGameSession() {
        viewModelScope.launch {
            val roomId = xoSocketService.initSession(args.username).data as String
            updateRoomId(roomId)
            notifyFriendJoin()
        }

    }

    private suspend fun notifyFriendJoin() {
        xoSocketService.observeGame(onFriendNotify = {
            _state.update { it.copy(isFriendActive = true) }
        }).collectLatest { }
    }

    private fun updateRoomId(roomId: String) {
        _state.update { it.copy(roomId = roomId) }
    }

    fun closeSession(){
        viewModelScope.launch {
            xoSocketService.closeSession()
        }
    }
}