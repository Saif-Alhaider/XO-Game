package com.example.xogame.ui.screen.join_game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.xogame.R
import com.example.xogame.ui.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.composables.XoScaffold
import com.example.xogame.ui.screen.home.composables.PrimaryButton
import com.example.xogame.ui.screen.play.navigateToPlay
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme
import com.example.xogame.util.createToast

@Composable
fun JoinGameScreen(viewModel: JoinGameViewModel = hiltViewModel(), navController: NavController) {
    val state = viewModel.state.collectAsState().value
    JoinGameContent(
        updateRoomId = viewModel::updateRoomId,
        onClickJoin = viewModel::onClickJoin,
        onNavigateBack = {
            navController.popBackStack()
            viewModel.closeSession()
        },
        onJoinSession = {
            navController.navigateToPlay("O")
            viewModel.dissableIsJoin()
        },
        state = state
    )
}

@Composable
fun JoinGameContent(
    updateRoomId: (String) -> Unit,
    onClickJoin: () -> Unit,
    onNavigateBack: () -> Unit,
    onJoinSession: () -> Unit,
    state: JoinGameUiState
) {
    val context = LocalContext.current

    if (state.isJoined) {
        onJoinSession()
    }
    BackHandler {
        onNavigateBack()
    }
    XoScaffold {
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
                    color = XOGameCustomColors.current.onBackground87,
                    modifier = Modifier.padding(top = 40.dp)
                )
                OutlinedTextFieldPrimary(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    value = state.roomId,
                    readOnly = false,
                    onValueChanged = updateRoomId,
                    placeHolder = "Enter Room Id",
                )
            }
            PrimaryButton(
                onClick = {
                    when {
                        state.roomId.isBlank() -> {
                            createToast(context, "please fill text field first")
                        }
//                        !isValidUUID(state.roomId) -> {
//                            createToast(context, "room id is not valid")
//                        }
                        else -> onClickJoin()
                    }
                },
                text = "Join Game",
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
fun JoinGamePreview() {
    XOGameTheme {
        JoinGameScreen(navController = rememberNavController())
    }
}
