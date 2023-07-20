package com.example.xogame.ui.screen.start_game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.XORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartGameViewModel @Inject constructor(
    private val xoRepository: XORepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(StartGameUiState())
    val state = _state.asStateFlow()

    private val args = StartGameArgs(savedStateHandle)
    var job: Job? = null

    init {
        _state.update { it.copy(firstPlayerName = xoRepository.getPlayerName() ?: "") }
        createGameSession()
    }

    private fun createGameSession() {
        viewModelScope.launch {
            val roomId = xoRepository.newGame(args.username).data?.let { it as String } ?: ""
            if (roomId.isNotBlank()) {
                updateRoomId(roomId)
                notifyFriendJoin()
            }
        }

    }

    private suspend fun notifyFriendJoin() {
        job = viewModelScope.launch {
            xoRepository.observeGame(onFriendNotify = { name ->
                _state.update { it.copy(isFriendActive = true) }
                _state.update { it.copy(player2Name = name) }
            }).collectLatest {}
        }
    }

    private fun updateRoomId(roomId: String) {
        _state.update { it.copy(roomId = roomId, isLoading = false) }

    }

    fun closeSession() {
        viewModelScope.launch {
            xoRepository.endGame()
        }
    }

    fun disableFriend() {
        job?.cancel()
        _state.update { it.copy(isFriendActive = false) }
    }


}