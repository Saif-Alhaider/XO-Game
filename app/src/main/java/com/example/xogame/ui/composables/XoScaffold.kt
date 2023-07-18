package com.example.xogame.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.xogame.ui.theme.XOGameCustomColors


@Composable
fun XoScaffold(
    content: @Composable (Modifier) -> Unit,
) {
    Box(
        Modifier
            .background(XOGameCustomColors.current.background)
            .fillMaxSize()) {

        Box(Modifier.fillMaxSize()) {
            Spacer(
                modifier = Modifier
                    .size(300.dp)
                    .blur(70.dp)
                    .padding(end = 80.dp, top = 80.dp)
                    .clip(CircleShape)
                    .background(color = XOGameCustomColors.current.primaryPink.copy(alpha = .4f))
                    .align(Alignment.BottomStart)
            )
            Spacer(
                modifier = Modifier
                    .size(300.dp)
                    .blur(70.dp)
                    .padding(start = 80.dp, bottom = 80.dp)
                    .clip(CircleShape)
                    .background(color = XOGameCustomColors.current.primaryBlue.copy(alpha = .4f))
                    .align(Alignment.TopEnd)
            )
        }
        content(Modifier)

    }
}

