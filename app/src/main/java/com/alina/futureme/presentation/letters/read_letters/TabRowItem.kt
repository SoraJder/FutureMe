package com.alina.futureme.presentation.letters.read_letters

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabRowItem(
    val title: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)