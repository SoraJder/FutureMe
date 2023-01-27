package com.alina.futureme.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryLightColor,
    primaryVariant = PrimaryColor,
    secondary = SecondaryColor
)

private val LightColorPalette = lightColors(
    primary = PrimaryDarkColor,
    primaryVariant = PrimaryColor,
    secondary = SecondaryColor
)

@Composable
fun FutureMeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}