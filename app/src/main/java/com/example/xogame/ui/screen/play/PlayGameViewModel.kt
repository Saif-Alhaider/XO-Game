package com.example.xogame.ui.screen.play

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.Game
import com.example.xogame.data.remote.XOSocketService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayGameViewModel @Inject constructor(
    private val xoSocketService: XOSocketService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(PlayUiState())
    val state = _state.asStateFlow()

    init {
        try {
            viewModelScope.launch {
                xoSocketService.initSession("Asiasama")
                xoSocketService.observeGame(onFriendNotify = {}).collectLatest { game ->

                    if (game != null) {
                        val list = _state.value.board.toMutableList()
                        val newRow = list[game.row].toMutableList()
                        newRow[game.column] = if (_state.value.turn == "X") "O" else "X"
                        list[game.row] = newRow
                        _state.update { it.copy(board = list, isActive = true) }
                        Log.e("TAG", "init: ${_state.value.board}")
                        Log.e("TAG", "init: ${game.row} ${game.column}")
                    }

                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "init error: ${e.message}")
        }
    }

    fun disablePosition(){
        _state.update {
            it.copy(isActive = false)
        }
    }

    fun onClickSquare(row: Int, column: Int) {
        Log.e("TAG", "onClickSquare: $row $column")
        try {
            viewModelScope.launch {
                val list = _state.value.board.toMutableList()
                val newRow = list[row].toMutableList()
                newRow[column] = _state.value.turn
                list[row] = newRow


                Log.e("TAGG", "$list")
                _state.update {
                    it.copy(board = list)
                }
                xoSocketService.sendXO(Game(row, column))
            }
        } catch (e: Exception) {
            Log.e("TAG", "message error: ${e.message}")
        }

    }

    fun closeSession() {
        viewModelScope.launch {
            xoSocketService.closeSession()
        }
    }
}