package com.example.xogame.ui.screen.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.xogame.R
import com.example.xogame.ui.composables.GameResultDialog
import com.example.xogame.ui.screen.play.composable.PlayCard
import com.example.xogame.ui.composables.Scaffold
import com.example.xogame.ui.screen.play.composable.PlayerLabel
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun PlayGameScreen() {
    PlayGameContent()
}

@Composable
fun PlayGameContent() {
    Scaffold {

        Box(modifier = Modifier.zIndex(4f)) {

            GameResultDialog(showDialog = false, winner = null ,modifier = Modifier.zIndex(5f))

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(
                            color = XOGameCustomColors.current.card,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.two_player_game),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall,
                        color = XOGameCustomColors.current.onBackground87
                    )
                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PlayerLabel(
                            playerName = "Meme",
                            playerSymbol = R.drawable.ic_x_palyer,
                            colorName = XOGameCustomColors.current.primaryBlue,
                        )
                        Text(
                            text = stringResource(R.string.vs),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = XOGameCustomColors.current.onBackground87
                        )
                        PlayerLabel(
                            playerName = "Asia",
                            playerSymbol = R.drawable.ic_o_player,
                            colorName = XOGameCustomColors.current.primaryPink,
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(9, key = { it }) {
                        PlayCard(modifier = Modifier)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StartGamePreview() {
    XOGameTheme { PlayGameScreen() }
}