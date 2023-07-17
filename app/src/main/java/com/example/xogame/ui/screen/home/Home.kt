package com.example.xogame.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogame.R
import com.example.xogame.ui.composables.OutlinedTextFieldPrimary
import com.example.xogame.ui.composables.XoScaffold
import com.example.xogame.ui.composables.PrimaryButton
import com.example.xogame.ui.theme.XOGameCustomColors
import com.example.xogame.ui.theme.XOGameTheme

@Composable
fun HomeScreen() {
    HomeContent()
}

@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContent() {
    XoScaffold {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            //region title
            Text(
                text = stringResource(R.string.tik_tak_toe), style = MaterialTheme.typography.titleMedium.copy(
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
                PrimaryButton(text = stringResource(R.string.start_game), onClick = {})
                Spacer(modifier = Modifier.height(12.dp))
                PrimaryButton(text = stringResource(R.string.join_game), onClick = {})
            }
            //endregion
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    XOGameTheme {
        HomeContent()
    }
}