package com.example.xogame.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.xogame.R
import com.example.xogame.ui.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.composables.XoScaffold
import com.example.xogame.ui.screen.home.composables.PrimaryButton
import com.example.xogame.ui.screen.join_game.navigateToJoinGame
import com.example.xogame.ui.screen.start_game.navigateToStartGame
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme
import com.example.xogame.ui.theme.XONavigationProvider
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    val navController  = XONavigationProvider.current

    HomeContent(
        onClickStart = { navController.navigateToStartGame(state.username) },
        updateUsername = viewModel::updateUsername,
        onClickJoin = { navController.navigateToJoinGame(state.username) },
        state = state,
        savePlayerName = viewModel::savePlayerName,
    )
}

@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    updateUsername: (String) -> Unit,
    onClickStart: () -> Unit,
    onClickJoin: () -> Unit,
    state: HomeUiState,
    savePlayerName: (String) -> Unit,
) {
    val context = LocalContext.current
    XoScaffold {
        Box(
            Modifier
                .fillMaxSize()

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
                    text = stringResource(R.string.tik_tak_toe),
                    style = MaterialTheme.typography.titleLarge.copy(
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
                    onValueChanged = updateUsername,
                    placeHolder = "Enter your name",
                    value = state.username
                )
                //endregion
                //region start and join game
                Column(Modifier.padding(top = 48.dp)) {
                    PrimaryButton(text = stringResource(R.string.start_game)) {
                        if (state.username.isNotBlank()) {
                            savePlayerName(state.username)
                            onClickStart()
                        } else {
                            Toast.makeText(context, "Please Fill Name First", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    PrimaryButton(
                        text = stringResource(R.string.join_game),
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        if (state.username.isNotBlank()) {
                            savePlayerName(state.username)
                            onClickJoin()
                        } else {
                            Toast.makeText(context, "Please Fill Name First", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
                //endregion
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    XOGameTheme {
        HomeScreen()
    }
}