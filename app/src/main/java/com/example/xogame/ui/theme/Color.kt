package com.example.xogame.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


val PrimaryBlue = Color(0xFF6A97F2)
val PrimaryBlue60 = Color(0x996A97F2)
val PrimaryPink = Color(0xFFF85C9D)
val PrimaryPink60 = Color(0x99F85C9D)
val Background = Color(0xFFFFFFFF)
val OnBackgroundLight87 = Color(0xDE000000)
val OnBackgroundLight60 = Color(0x99000000)
val OnBackgroundLight36 = Color(0x99000000)
val OnButton = Color(0xDEFFFFFF)
val CardLight = Color(0x5CFFFFFF)
val GameCardLight = Color(0xFFFAFAFA)

@Immutable
data class CustomColorsPalette(
    val primaryBlue: Color = Color.Unspecified,
    val primaryBlue60: Color = Color.Unspecified,
    val primaryPink: Color = Color.Unspecified,
    val primaryPink60: Color = Color.Unspecified,
    val onBackground87: Color = Color.Unspecified,
    val onBackground60: Color = Color.Unspecified,
    val onBackground36: Color = Color.Unspecified,
    val onButton:Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val gameCard: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
)