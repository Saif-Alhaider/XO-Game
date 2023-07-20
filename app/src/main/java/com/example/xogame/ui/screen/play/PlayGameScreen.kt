package com.example.xogame.ui.screen.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.xogame.R
import com.example.xogame.ui.composables.GamePresentation
import com.example.xogame.ui.composables.XoScaffold
import com.example.xogame.ui.screen.AppDestination
import com.example.xogame.ui.screen.play.composable.GameResultDialog
import com.example.xogame.ui.screen.play.composable.PlayCard
import com.example.xogame.ui.screen.play.composable.PlayerLabel
import com.example.xogame.ui.screen.start_game.navigateToStartGame
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme
import com.example.xogame.ui.theme.XONavigationProvider

@Composable
fun PlayGameScreen(
    viewModel: PlayGameViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value
    val navController = XONavigationProvider.current

    PlayGameContent(
        state = state,
        onClickSquare = viewModel::onClickSquare,
        onClickCard = viewModel::disablePosition,
        onBackToPlayAgain = { navController.navigateToStartGame(state.firstPlayerName)},
        onBackToMenu = { navController.popBackStack(AppDestination.Home.route, false) }
    )
}

@Composable
fun PlayGameContent(
    state: PlayUiState,
    onClickSquare: (Int, Int) -> Unit,
    onClickCard: () -> Unit,
    onBackToPlayAgain: () -> Unit,
    onBackToMenu: () -> Unit,

    ) {
    XoScaffold {

        Box {
            GameResultDialog(
                showDialog = state.winner.isNotEmpty(),
                winner = state.winner,
                modifier = Modifier.zIndex(2f),
                onBackToPlayAgain = onBackToPlayAgain,
                onBackToMenu = onBackToMenu
            )
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
                            playerName = state.firstPlayerName,
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
                            playerName = state.secondPlayerName,
                            playerSymbol = R.drawable.ic_o_player,
                            colorName = XOGameCustomColors.current.primaryPink,
                        )
                    }
                }
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    state.board.forEachIndexed { row, strings ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            strings.forEachIndexed { column, _ ->
                                PlayCard(
                                    modifier = Modifier,
                                    value = state.board[row][column],
                                    onClick = { onClickSquare(row, column) },
                                    playerTurn = state.currentPlayer,
                                    isActive = state.isActive,
                                    onClickCard = onClickCard
                                )
                            }
                        }
                    }
                }
            }
            GamePresentation()
        }
    }
}

@Preview
@Composable
fun StartGamePreview() {
    XOGameTheme { PlayGameScreen() }
}


