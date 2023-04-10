package com.alina.futureme.presentation.letters.write_letter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alina.futureme.R
import com.alina.futureme.common.Utils
import com.alina.futureme.components.TransparentHintTextField
import com.alina.futureme.presentation.theme.Typography

@Composable
fun WriteLetterScreen() {

    var letterTitle by rememberSaveable {
        mutableStateOf("A letter from " + Utils.getDate())
    }

    val letterText = remember {
        mutableStateOf("")
    }

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TransparentHintTextField(
            text = letterTitle,
            hint = stringResource(R.string.name_this_letter_hint),
            onValueChange = {
                if (it.length <= 100) {
                    letterTitle = it
                }
            },
            textStyle = Typography.titleMedium,
            singleLine = true,
            modifier = Modifier.padding(24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(horizontal = 24.dp)
                .shadow(
                    elevation = 8.dp,
                    ambientColor = MaterialTheme.colorScheme.scrim,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { focusRequester.requestFocus() },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
           TransparentHintTextField(
                text = letterText.value,
                hint = "Dear future me...",
                onValueChange = { letterText.value = it },
                textStyle = Typography.bodyLarge,
                singleLine = false,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .focusRequester(focusRequester)
            )
        }

        TextButton(
            onClick = {/*TODO apare bottom sheet cu idei*/ },
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(horizontal = 16.dp)

        ) {
            Text(
                text = stringResource(R.string.dont_have_any_idea),
                letterSpacing = 1.sp,
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            FloatingActionButton(
                onClick = { /*TODO poza instant permisiune*/ },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Icon(
                    Icons.Outlined.PhotoCamera,
                    contentDescription = null
                )
            }
            FloatingActionButton(
                onClick = { /*TODO video*/ },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Icon(
                    Icons.Outlined.Videocam,
                    contentDescription = null
                )
            }
            FloatingActionButton(
                onClick = { /*TODO poza */ },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,

                ) {
                Icon(
                    Icons.Outlined.Image,
                    contentDescription = null,
                )
            }
        }
    }
}