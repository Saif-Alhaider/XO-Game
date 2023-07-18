package com.example.xogame.ui.screen.start_game

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.xogame.R
import com.example.xogame.ui.composables.LoadingScreen
import com.example.xogame.ui.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.composables.XoScaffold
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun StartGameScreen(viewModel: StartGameViewModel = hiltViewModel(), navController: NavController) {
    val state = viewModel.state.collectAsState().value
    StartGameContent(onNavigateBack = {
        navController.popBackStack()
        viewModel.closeSession()
    }, state)
}

@Composable
fun StartGameContent(onNavigateBack: () -> Unit, state: StartGameUiState) {
    val clipboardManager = LocalClipboardManager.current
    if (state.isFriendActive) {
        Log.i("gg", "StartGameContent: navigate now")
    }
    BackHandler {
        onNavigateBack()
    }
    XoScaffold {
        Box(Modifier.fillMaxSize()) {
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
                    OutlinedTextFieldPrimary(trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = "copy",
                            modifier = Modifier
                                .clickable { clipboardManager.setText(AnnotatedString((state.roomId))) }
                        )
                    }, modifier = Modifier.padding(top = 16.dp), value = state.roomId, enabled = false)
                    Text(
                        text = "When your friend joins the game, you'll be ready to have fun playing together",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = XOGameCustomColors.current.onBackground60,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            AnimatedVisibility(visible = state.isLoading, exit = fadeOut()) {
                LoadingScreen()
            }
        }
    }
}

@Preview
@Composable
fun StartGamePreview() {
    XOGameTheme { StartGameScreen(navController = rememberNavController()) }
}