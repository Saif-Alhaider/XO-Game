package com.example.xogame.ui.screen.play.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.xogame.R
import com.example.xogame.ui.screen.play.PlayUiState
import com.example.xogame.ui.theme.XOGameCustomColors

@Composable
fun PlayCard(
    modifier: Modifier = Modifier,
    value: PlayUiState.XOCard,
    onClick: () -> Unit,
    playerTurn: String? = null,
    isActive: Boolean,
    onClickCard: () -> Unit
) {
    Box(
        modifier = modifier
            .width(104.dp)
            .height(106.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = if (value.color == Color.Transparent) XOGameCustomColors.current.gameCard else value.color
            )
            .clickable(enabled = value.value.isBlank() && isActive)
            {
                onClick()
                onClickCard()
                Log.e("TAG", "PlayCard: $value")
            }
    ) {
        (
                when (value.value) {
                    "X" -> painterResource(id = R.drawable.ic_x_palyer)
                    "O" -> painterResource(
                        id = R.drawable.ic_o_player
                    )

                    else -> null
                })?.let {
            Image(
                painter = it,
                contentDescription = null,
                colorFilter = if (value.color != Color.Transparent) ColorFilter.tint(color = Color.White) else null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
        }
    }

}