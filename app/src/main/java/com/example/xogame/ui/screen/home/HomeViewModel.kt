package com.example.xogame.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.Game
import com.example.xogame.data.remote.XOSocketServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val xoSocketServiceImpl: XOSocketServiceImpl
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()
    fun onClickStartGame() {
        viewModelScope.launch {
            xoSocketServiceImpl.initSession(_state.value.username)
        }
    }

    fun updateUsername(text: String) {
        _state.update { it.copy(username = text) }
    }

    fun onClickJoin() {
//        viewModelScope.launch {
//            xoSocketServiceImpl.joinSession("mohammad", "1c369196-5c96-41df-9118-dd2379625a15")
//            xoSocketServiceImpl.observeGame()
//                .collectLatest { xoSocketServiceImpl.sendXO(Game(2, 2)) }
//        }
    }
}