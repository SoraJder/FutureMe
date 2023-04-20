package com.alina.futureme.presentation.letters.write_letter

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alina.futureme.R
import com.alina.futureme.common.Utils
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.components.TransparentHintTextField
import com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips.BottomSheetIdeasScreen
import com.alina.futureme.presentation.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteLetterScreen(
    viewModel: WriteLetterViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState()
    val focusRequester = remember { FocusRequester() }

    val letterTitle by viewModel.letterTitle
    val letterText by viewModel.letterText
    val mediaFileUri by viewModel.mediaFile

    val singleMediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.updateMediaFile(uri) }
    )

    val context = LocalContext.current

    if (modalSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    modalSheetState.hide()
                }
            }
        ) {
            BottomSheetIdeasScreen(
                onSelected = {
                    coroutineScope.launch {
                        modalSheetState.hide()
                    }
                }
            )
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
                    viewModel.updateTitle(it)
                }
            },
            textStyle = Typography.titleMedium,
            singleLine = true,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start)
        ) {

            Image(
                modifier = Modifier
                    .size(56.dp),
                painter = painterResource(id = R.drawable.gallery),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            when (mediaFileUri) {
                null -> {
                    FloatingActionButton(
                        onClick = {
                            singleMediaPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        shape = CircleShape,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.Outlined.Add,
                            contentDescription = null,
                        )
                    }
                }
                else -> {
                    mediaFileUri?.let { mediaFile ->
                        AsyncImage(
                            model = mediaFile,
                            contentDescription = null,
                            modifier = Modifier
                                .size(56.dp)
                                .clickable {
                                    viewModel.updateMediaFile(null)
                                },
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }

        TextButton(
            onClick = {
                coroutineScope.launch {
                    modalSheetState.show()
                }
            },
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(horizontal = 16.dp)

        ) {
            Text(
                text = stringResource(R.string.dont_have_any_idea),
                letterSpacing = 1.sp,
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(start= 24.dp, end = 24.dp, bottom = 16.dp)
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

        PrimaryButton(
            text = stringResource(id = R.string.send),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            onClick = {
                if (letterText.isNotBlank() && letterTitle.isNotBlank()) {
                    //TODO
                } else {
                    Utils.showMessage(context, "Please fill the letter title or/and letter text")
                }
            },
        )
    }
}