package com.example.xogame.ui.screen.play.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xogame.R


@Composable
fun PlayerLabel(
    modifier: Modifier = Modifier,
    playerName: String,
    playerSymbol: Int,
    colorName: Color,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = playerSymbol),
            contentDescription = stringResource(R.string.player),
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = playerName,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = colorName
        )
    }
}