package com.alina.futureme.components

import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.alina.futureme.R
import com.alina.futureme.presentation.theme.Typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    text: String,
    placeholder: String,
    isPasswordTextField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit,
    showError: Boolean = false,
    errorMessage: String = ""
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(8.dp),
        label = { Text(text = placeholder, style = Typography.labelLarge) },
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
            .onPreviewKeyEvent {
                if (it.key == Key.Enter && it.nativeKeyEvent.action == ACTION_DOWN) {
                    focusManager.moveFocus(FocusDirection.Down)
                    true
                } else {
                    false
                }
            },
        isError = showError,
        trailingIcon = {
            if (isPasswordTextField) {
                IconButton(onClick = { onVisibilityChange(!isPasswordVisible) }) {
                    Icon(
                        painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_baseline_visibility_24) else painterResource(
                            id = R.drawable.ic_baseline_visibility_off_24
                        ),
                        contentDescription = "Tootle password visibility"
                    )
                }
            }
        },
        visualTransformation = when {
            isPasswordTextField && isPasswordVisible -> VisualTransformation.None
            isPasswordTextField -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )

    if (showError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = Typography.labelMedium,
            modifier = Modifier
                .padding(start = 10.dp)
                .offset(y = (-1).dp)
                .fillMaxWidth(0.9f)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    singleLine:Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = { value ->
                onValueChange(value)
            },
            textStyle = textStyle.plus(
                TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)
            ),
            cursorBrush = SolidColor(androidx.compose.material.MaterialTheme.colors.onBackground),
            modifier = Modifier
                .fillMaxWidth()
                .onPreviewKeyEvent {
                    if (it.key == Key.Enter && it.nativeKeyEvent.action == ACTION_DOWN) {
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    } else {
                        false
                    }
                },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                },
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            singleLine = singleLine,
        )

        if (text.isEmpty()) {
            Text(
                text = hint,
                style = textStyle,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5F)
            )
        }
    }
}