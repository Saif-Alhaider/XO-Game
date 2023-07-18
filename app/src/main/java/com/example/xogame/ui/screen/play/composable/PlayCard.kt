package com.example.xogame.ui.screen.play.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xogame.ui.theme.XOGameCustomColors

@Composable
fun PlayCard(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(104.dp)
            .height(106.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = XOGameCustomColors.current.gameCard
            )
    )
}