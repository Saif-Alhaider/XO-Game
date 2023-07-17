package com.example.xogame.ui.screen.start_game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.xogame.ui.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.composables.XoScaffold
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun StartGameScreen() {
    StartGameContent()
}

@Composable
fun StartGameContent() {
    XoScaffold {
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
                text = stringResource(R.string.copy_the_following_code_and_send_it_to_your_friend),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 40.dp)
            )
            OutlinedTextFieldPrimary(modifier = Modifier.padding(top = 16.dp),
                onValueChanged = {}, value = "") {
                Image(painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "copy",
                    modifier = Modifier.clickable { })
            }
            Text(
                text = stringResource(R.string.when_your_friend_joins_the_game_you_ll_be_ready_to_have_fun_playing_together),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = XOGameCustomColors.current.onBackground60,
                modifier = Modifier.padding(top = 24.dp)
            )

        }
    }
}

@Preview
@Composable
fun StartGamePreview() {
    XOGameTheme { StartGameScreen() }
}