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
        observeWinning()
    }

    private fun observeWinning() {
        viewModelScope.launch {
            _state.collectLatest {
                Log.i("gg", "winning pos:${winningPositions(_state.value.board)} ")
            }
        }
    }

    fun disablePosition() {
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

    private fun winningPositions(list: List<List<String>>): List<List<Int>> {
        val winningRows = mutableListOf<List<Int>>()
        val winningColumns = mutableListOf<List<Int>>()
        val diagonal = mutableListOf<List<Int>>()

        // horizontal
        for ((rowIndex, row) in list.withIndex()) {
            if (row.all { it.isNotBlank() && it == row[0] })
                winningRows.addAll(
                    listOf(
                        listOf(rowIndex, 0),
                        listOf(rowIndex, 1),
                        listOf(rowIndex, 2)
                    )
                )
        }
        //vertical
        for ((colIndex, _) in list.withIndex()) {
            if (list.all { it[colIndex].isNotBlank() && it[colIndex] == list[0][colIndex] }) {
                winningColumns.addAll(
                    listOf(
                        listOf(0, colIndex),
                        listOf(1, colIndex),
                        listOf(2, colIndex)
                    )
                )
            }
        }

        if ((list[0][0] == list[1][1] && list[1][1] == list[2][2]) && (list[0][0].isNotBlank()
                    && list[1][1].isNotBlank() && list[2][2].isNotBlank())
        )
            diagonal.addAll(
                listOf(
                    listOf(0, 0),
                    listOf(1, 1),
                    listOf(2, 2)
                )
            )
        if ((list[0][2] == list[1][1] && list[1][1] == list[2][0]) && (list[0][2].isNotBlank()
                    && list[1][1].isNotBlank() && list[2][0].isNotBlank())
        )
            diagonal.addAll(
                listOf(
                    listOf(0, 2),
                    listOf(1, 1),
                    listOf(2, 0)
                )
            )

        return winningRows + winningColumns + diagonal
    }
}