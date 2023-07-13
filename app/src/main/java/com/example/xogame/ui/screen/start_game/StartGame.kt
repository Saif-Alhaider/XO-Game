package com.example.xogame.ui.screen.start_game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun StartGameScreen() {
    StartGameContent()
}

@Composable
fun StartGameContent() {
    Box(Modifier.background(XOGameCustomColors.current.background)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.two_kids_playing_chess),
                contentDescription = "two kids playing chess"
            )
            Text(
                text = "Copy the following code and send it to your friend",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 40.dp)
            )
            OutlinedTextFieldPrimary(onValueChanged = {}, trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "copy"
                )
            }, modifier = Modifier.padding(top = 16.dp))
            Text(
                text = "When your friend joins the game, you'll be ready to have fun playing together",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = XOGameCustomColors.current.onBackground60,
                modifier=Modifier.padding(top = 24.dp)
            )
        }
        MainBackground()
    }
}

@Preview
@Composable
fun StartGamePreview() {
    XOGameTheme { StartGameScreen() }
}