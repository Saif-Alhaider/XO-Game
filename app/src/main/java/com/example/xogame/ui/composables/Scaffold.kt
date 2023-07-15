package com.example.xogame.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.xogame.ui.theme.XOGameCustomColors


@Composable
fun Scaffold(
    content: @Composable (Modifier) -> Unit,
) {
    Box(Modifier.background(XOGameCustomColors.current.background).fillMaxSize()) {

        content(Modifier.background(XOGameCustomColors.current.background))

        MainBackground()
    }
}