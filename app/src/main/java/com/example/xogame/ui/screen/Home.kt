package com.example.xogame.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogame.ui.common.composables.MainBackground
import com.example.xogame.ui.common.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.screen.composables.PrimaryButton
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun HomeScreen() {
    HomeContent()
}

@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContent() {
    val primaryPink = XOGameCustomColors.current.primaryPink
    val primaryBlue = XOGameCustomColors.current.primaryBlue
    Box(
        Modifier
            .fillMaxSize()
            .background(color = XOGameCustomColors.current.background)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()

                .padding(horizontal = 16.dp)
        ) {
            //region title
            Text(
                text = "TIK TAK TOE", style = MaterialTheme.typography.titleMedium.copy(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            XOGameCustomColors.current.primaryPink,
                            XOGameCustomColors.current.primaryBlue,
                        ),
                        tileMode = TileMode.Mirror
                    ),
                )
            )
            //endregion
            //region enter username
            OutlinedTextFieldPrimary(
                modifier = Modifier.padding(top = 48.dp),
                onValueChanged = {},
                placeHolder = "Enter your name"
            )
            //endregion
            //region start and join game
            Column(Modifier.padding(top = 48.dp)) {
                PrimaryButton(text = "Start Game", onClick = {})
                Spacer(modifier = Modifier.height(12.dp))
                PrimaryButton(text = "Join Game", onClick = {})
            }
            //endregion
        }
        MainBackground()
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    XOGameTheme {
        HomeContent()
    }
}