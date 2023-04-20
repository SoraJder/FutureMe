package com.alina.futureme.presentation.letters.write_letter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.common.Utils
import com.alina.futureme.components.TransparentHintTextField
import com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips.BottomSheetIdeasScreen
import com.alina.futureme.presentation.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteLetterScreen(
    viewModel: WriteLetterViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState()

    var letterTitle by rememberSaveable {
        mutableStateOf("A letter from " + Utils.getDate())
    }

    val letterText: String by viewModel.letterText.collectAsState()

    val focusRequester = remember { FocusRequester() }

    if (modalSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    modalSheetState.hide()
                }
            }
        ) {
            BottomSheetIdeasScreen()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
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
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
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
                text = letterText,
                hint = "Dear future me...",
                onValueChange = {
                    viewModel.updateText(it)
                },
                textStyle = Typography.bodyLarge,
                singleLine = false,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .focusRequester(focusRequester)
            )
        }

        TextButton(
            onClick = {
                coroutineScope.launch {
                    modalSheetState.show()
                }
            },
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