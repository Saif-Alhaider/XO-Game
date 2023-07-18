package com.example.xogame.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.data.XORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val xoRepository: XORepository) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun updateUsername(text: String) {
        _state.update { it.copy(username = text) }
    }

    fun savePlayerName(name: String) {
        viewModelScope.launch {
            xoRepository.savePlayerName(name)
        }
    }

    suspend fun getPlayerName(): String? {
        return xoRepository.getPlayerName()
    }

    fun clearPlayerName() {
        viewModelScope.launch {
            xoRepository.clearPlayerName()
        }
    }

}