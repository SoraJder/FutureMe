package com.alina.futureme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alina.futureme.presentation.theme.PrimaryLightColor

@Composable
fun TextWithLinesOnSides(text: String) {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier.weight(0.8f),
            color = PrimaryLightColor, thickness = 2.dp
        )
        Text(
            text = text, modifier = Modifier
                .weight(0.2f),
            style = TextStyle(textAlign = TextAlign.Center)
        )
        Divider(
            modifier = Modifier.weight(0.8f),
            color = PrimaryLightColor, thickness = 2.dp
        )
    }
}