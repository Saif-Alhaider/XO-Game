package com.example.xogame.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = PrimaryPink,
    tertiary = PrimaryBlue60
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = PrimaryPink,
    tertiary = PrimaryBlue60
)
private val LightCustomColorPlate = CustomColorsPalette(
    primaryBlue = PrimaryBlue,
    primaryBlue60 = PrimaryBlue60,
    primaryPink = PrimaryPink,
    primaryPink60 = PrimaryPink60,
    background = Background,
    onBackground87 = OnBackgroundLight87,
    onBackground60 = OnBackgroundLight60,
    onBackground36 = OnBackgroundLight36,
    card = CardLight,
    gameCard = GameCardLight
)
private val DarkCustomColorPlate = CustomColorsPalette(
    primaryBlue = PrimaryBlue,
    primaryBlue60 = PrimaryBlue60,
    primaryPink = PrimaryPink,
    primaryPink60 = PrimaryPink60,
    background = Background,
    onBackground87 = OnBackgroundLight87,
    onBackground60 = OnBackgroundLight60,
    onBackground36 = OnBackgroundLight36,
    card = CardLight,
    gameCard = GameCardLight
)

@SuppressLint("CompositionLocalNaming")
val XOGameCustomColors = staticCompositionLocalOf { CustomColorsPalette() }

@Composable
fun XOGameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    val customColorsPalette = if (darkTheme) DarkCustomColorPlate else LightCustomColorPlate

    CompositionLocalProvider(
        XOGameCustomColors provides customColorsPalette
    ){
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}