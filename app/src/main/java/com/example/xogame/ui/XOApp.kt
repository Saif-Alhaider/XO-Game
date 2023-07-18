package com.example.xogame.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.xogame.ui.theme.XOGameTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun XOApp(){
    XOGameTheme {
        Scaffold {
            val navController = rememberNavController()
        }
    }
}