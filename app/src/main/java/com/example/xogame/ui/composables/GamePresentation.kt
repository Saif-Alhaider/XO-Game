package com.example.xogame.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.xogame.R
import com.example.xogame.ui.theme.XOGameCustomColors

@Composable
fun GamePresentation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.vs))
    val progress by animateLottieCompositionAsState(composition)

    AnimatedVisibility(
        visible = progress != 1f,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .background(
                    XOGameCustomColors.current.onBackground36
                )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Saif", fontSize = 40.sp, color = Color.White)
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(150.dp),
                )
                Text(text = "Asia", fontSize = 40.sp, color = Color.White)
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun GamePresentationPreview() {
    GamePresentation()
}