package com.example.xogame.ui.screen.play.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogame.R
import com.example.xogame.ui.screen.home.composables.PrimaryButton
import com.example.xogame.ui.theme.XOGameCustomColors


@Composable
fun GameResultDialog(
    modifier: Modifier = Modifier,
    winner: String,
    showDialog: Boolean = false,
    onBackToPlayAgain: () -> Unit,
    onBackToMenu: () -> Unit
) {

    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = showDialog,
            enter = fadeIn(animationSpec = tween(100, delayMillis = 500)),
            exit = fadeOut(animationSpec = tween(100, delayMillis = 500))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(XOGameCustomColors.current.onBackground36)
            ) {

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            color = XOGameCustomColors.current.background,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .wrapContentHeight()
                        .width(284.dp)
                        .padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedVisibility(
                        visible = (winner != "Draw"),
                        enter = fadeIn(animationSpec = tween(100)),
                        exit = fadeOut(animationSpec = tween(100))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = stringResource(R.string.player),
                            modifier = Modifier.width(160.dp)
                        )
                    }
                    Text(
                        text = if (winner != "Draw") "$winner Win" else "DRAW",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 18.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = XOGameCustomColors.current.onBackground87
                    )
                    PrimaryButton(
                        text = stringResource(R.string.play_again),
                        onClick = { onBackToPlayAgain() },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 24.dp)
                    )
                    PrimaryButton(
                        text = stringResource(R.string.main_menu),
                        onClick = { onBackToMenu() },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 12.dp)
                    )
                }
            }
        }
    }
}
