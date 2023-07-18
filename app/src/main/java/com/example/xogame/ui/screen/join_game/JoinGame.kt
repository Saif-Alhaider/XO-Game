package com.example.xogame.ui.screen.join_game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogame.R
import com.example.xogame.ui.common.composables.MainBackground
import com.example.xogame.ui.common.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.screen.home.composables.PrimaryButton
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun JoinGameScreen() {
    JoinGameContent()
}

@Composable
fun JoinGameContent() {
    Box(Modifier.background(XOGameCustomColors.current.background)) {
        MainBackground()
        Box(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Column(
                Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.two_kids_playing_chess),
                    contentDescription = "two kids playing chess"
                )
                Text(
                    text = "Please enter game ID to join",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp)
                )
                OutlinedTextFieldPrimary(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(56.dp).fillMaxWidth(),
                    value = "",
                    enabled = true
                )
            }
            PrimaryButton(
                onClick = {},
                text = "Join Game",
                modifier = Modifier.padding(bottom = 40.dp).align(Alignment.BottomCenter)
            )
        }

    }
}

@Preview
@Composable
fun JoinGamePreview() {
    XOGameTheme {
        JoinGameScreen()
    }
}