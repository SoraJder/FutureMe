package com.alina.futureme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alina.futureme.R
import com.alina.futureme.presentation.theme.Typography

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                style = Typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun PrimaryButtonWithContent(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
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
                style = Typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun FAQButton(
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .width(80.dp),
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(R.string.faq),
            style = Typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun ProfileButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            Icons.Default.Person,
            contentDescription = null
        )
    }
}