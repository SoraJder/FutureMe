package com.alina.futureme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alina.futureme.presentation.theme.PrimaryColor
import com.alina.futureme.presentation.theme.PrimaryTextColor
import com.alina.futureme.presentation.theme.SecondaryColor
import com.alina.futureme.presentation.theme.Typography

@Composable
fun PrimaryButton(
    text:String,
    modifier: Modifier,
    onClick: () ->Unit,
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = SecondaryColor
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                style = Typography.button,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 4.dp),
                color = PrimaryTextColor
            )
        }
    }
}

@Composable
fun PrimaryButtonWithContent(
    text:String,
    modifier: Modifier,
    onClick: () ->Unit,
    content: @Composable () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = SecondaryColor
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            content()
            Text(
                text = text,
                style = Typography.button,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 4.dp),
                color = PrimaryTextColor
            )
        }
    }
}