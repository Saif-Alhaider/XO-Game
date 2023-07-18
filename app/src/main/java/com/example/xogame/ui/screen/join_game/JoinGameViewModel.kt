package com.example.xogame.ui.screen.join_game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.remote.XOSocketService
import com.example.xogame.util.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinGameViewModel @Inject constructor(
    private val xoSocketService: XOSocketService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(JoinGameUiState())
    val state = _state.asStateFlow()

    private val args = JoinGameArgs(savedStateHandle)

    fun updateRoomId(roomId: String) {
        _state.update { it.copy(roomId = roomId) }
    }

    fun onClickJoin() {
        viewModelScope.launch {
            val response =
                xoSocketService.joinSession(username = args.username, roomId = _state.value.roomId)
            when (response) {
                is ResponseResult.Success -> _state.update { it.copy(isJoined = true) }
                is ResponseResult.Error -> _state.update { it.copy(isJoined = false) }
            }
        }
    }

    fun closeSession() {
        viewModelScope.launch {
            xoSocketService.closeSession()
        }
    }
}