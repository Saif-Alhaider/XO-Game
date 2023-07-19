package com.example.xogame.data.util


data class  JoinedTheGameWithException(val playerName : String) : Exception()
data class NotYourTurnException(val yourTurn : Boolean) : Exception()
data class WinnerException(val winnerName : String) : Exception()
class PositionIsNotEmptyException : Exception()