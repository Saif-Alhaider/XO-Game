package com.example.xogame.data.util

object NotYourTurnException : Exception()
data class WinnerException(val winnerName : String) : Exception()
