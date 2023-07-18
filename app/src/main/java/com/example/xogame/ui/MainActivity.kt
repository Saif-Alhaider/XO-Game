package com.example.xogame.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.xogame.ui.screen.XOGameNavGraph
import com.example.xogame.ui.theme.XOGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            XOGameTheme {
                val navController = rememberNavController()
                XOGameNavGraph(navHostController = navController)
            }
        }
    }
}
