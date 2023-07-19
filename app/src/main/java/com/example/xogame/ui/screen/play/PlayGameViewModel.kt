package com.example.xogame.ui.screen.play

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.Game
import com.example.xogame.data.remote.XOSocketService
import com.example.xogame.data.util.FriendJoinedTheGameException
import com.example.xogame.data.util.NotYourTurnException
import com.example.xogame.data.util.PositionIsNotEmptyException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
    private val args = PlayArgs(savedStateHandle)

    init {
        updateCharacter()
        if (_state.value.currentPlayer == "O") disablePosition()
        observeGame()
        observeWinning()
    }

    private fun updateCharacter() {
        _state.update { it.copy(currentPlayer = args.character) }
    }

    private fun observeGame() {

        viewModelScope.launch {
            try {
                xoSocketService.observeGame(onFriendNotify = {}).collectLatest { game ->
                    Log.i("gg", "observeGame: $game")
                    if (game != null) {
                        val list = _state.value.board.toMutableList()
                        val newRow = list[game.row].toMutableList()
                        newRow[game.column] =
                            newRow[game.column].copy(value = if (_state.value.currentPlayer == "X") "O" else "X")

                        list[game.row] = newRow
                        _state.update { it.copy(board = list, isActive = true) }
                        Log.e("TAG", "init: ${_state.value.board}")
                        Log.e("TAG", "init: ${game.row} ${game.column}")
                    }

                }
            } catch (e: FriendJoinedTheGameException) {
                Log.e("TAG", "FriendJoinedTheGameException: ${e.message}")
            } catch (e: PositionIsNotEmptyException) {
                Log.e("TAG", "PositionIsNotEmptyException: ${e.message}")
            } catch (e: NotYourTurnException) {
                Log.e("TAG", "NotYourTurnException: ${e.message}")
            }
        }
    }

    private fun observeWinning() {
        viewModelScope.launch {
            _state.map { it.board }.distinctUntilChanged().collectLatest {
                if (winningPositions(_state.value.board).isNotEmpty()) {
                    val newState =
                        winningPositions(_state.value.board).fold(state.value) { accState, (row, col) ->
                            updateColor(accState, row, col)
                        }
                    _state.update { it.copy(board = newState.board) }
                }
                Log.i("gg", "winning pos:${winningPositions(_state.value.board)} ")
                Log.i("gg", "board:${_state.value.board}")
            }
        }
    }

    private fun updateColor(state: PlayUiState, row: Int, col: Int): PlayUiState {
        val updatedBoard = state.board.mapIndexed { rowIndex, rowList ->
            rowList.mapIndexed { colIndex, card ->
                if (rowIndex == row && colIndex == col) {
                    card.copy(color = if (card.value == "X") Color.Blue else Color.Magenta)
                } else {
                    card
                }
            }
        }
        return state.copy(board = updatedBoard)
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
                newRow[column] =
                    newRow[column].copy(value = _state.value.currentPlayer)
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

    private fun winningPositions(list: List<List<PlayUiState.XOCard>>): List<Pair<Int, Int>> {
        val winningRows = mutableListOf<List<Int>>()
        val winningColumns = mutableListOf<List<Int>>()
        val diagonal = mutableListOf<List<Int>>()

        // horizontal
        for ((rowIndex, row) in list.withIndex()) {
            if (row.all { it.value.isNotBlank() && it == row[0] })
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
            if (list.all { it[colIndex].value.isNotBlank() && it[colIndex] == list[0][colIndex] }) {
                winningColumns.addAll(
                    listOf(
                        listOf(0, colIndex),
                        listOf(1, colIndex),
                        listOf(2, colIndex)
                    )
                )
            }
        }
        // left diagonal
        if ((list[0][0].value == list[1][1].value && list[1][1].value == list[2][2].value) && (list[0][0].value.isNotBlank()
                    && list[1][1].value.isNotBlank() && list[2][2].value.isNotBlank())
        )
            diagonal.addAll(
                listOf(
                    listOf(0, 0),
                    listOf(1, 1),
                    listOf(2, 2)
                )
            )
        //right diagonal
        if ((list[0][2].value == list[1][1].value && list[1][1].value == list[2][0].value) && (list[0][2].value.isNotBlank()
                    && list[1][1].value.isNotBlank() && list[2][0].value.isNotBlank())
        )
            diagonal.addAll(
                listOf(
                    listOf(0, 2),
                    listOf(1, 1),
                    listOf(2, 0)
                )
            )

        return (winningRows + winningColumns + diagonal).map { (first, second) ->
            Pair(
                first,
                second
            )
        }
    }
}