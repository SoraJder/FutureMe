package com.alina.futureme.presentation.letters.write_letter

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alina.futureme.common.Utils
import com.alina.futureme.R
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.components.TransparentHintTextField
import com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips.BottomSheetIdeasScreen
import com.alina.futureme.presentation.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteLetterScreen(
    viewModel: WriteLetterViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Black)

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState()
    val focusRequester = remember { FocusRequester() }

    val letterTitle by viewModel.letterTitle
    val letterText by viewModel.letterText
    val mediaFileUri by viewModel.mediaFile
    val email by viewModel.email
    val isPublic by viewModel.isPublic
    val selectedDate by viewModel.selectedDate

    val singleMediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.updateMediaFile(uri) }
    )

    val selectSpecificDate = rememberUseCaseState()
    val selectRandomDate = rememberUseCaseState()
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

    //calendar dialog pentru alegerea unei date specifice
    //la boundary este un range care este desemnat prin ".."
    CalendarDialog(
        state = selectSpecificDate,
        config = CalendarConfig(
            yearSelection = false,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = LocalDate.now().plusDays(1)..LocalDate.now().plusYears(20L)
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate
        ) { newDate ->
            viewModel.updateSelectedDate(newDate)
            selectSpecificDate.finish()
        }
    )

    //calendar pentru alegerea unui an
    val optionsYears = listOf(
        Option(titleText = LocalDate.now().year.toString()),
        Option(titleText = LocalDate.now().plusYears(1).year.toString()),
        Option(titleText = LocalDate.now().plusYears(2).year.toString()),
        Option(titleText = LocalDate.now().plusYears(3).year.toString()),
        Option(titleText = LocalDate.now().plusYears(4).year.toString()),
        Option(titleText = LocalDate.now().plusYears(5).year.toString()),
        Option(titleText = LocalDate.now().plusYears(6).year.toString()),
        Option(titleText = LocalDate.now().plusYears(7).year.toString()),
        Option(titleText = LocalDate.now().plusYears(8).year.toString()),
        Option(titleText = LocalDate.now().plusYears(9).year.toString()),
        Option(titleText = LocalDate.now().plusYears(10).year.toString()),
        Option(titleText = LocalDate.now().plusYears(11).year.toString()),
        Option(titleText = LocalDate.now().plusYears(12).year.toString()),
        Option(titleText = LocalDate.now().plusYears(13).year.toString()),
        Option(titleText = LocalDate.now().plusYears(14).year.toString()),
        Option(titleText = LocalDate.now().plusYears(15).year.toString()),
        Option(titleText = LocalDate.now().plusYears(16).year.toString()),
        Option(titleText = LocalDate.now().plusYears(17).year.toString()),
        Option(titleText = LocalDate.now().plusYears(18).year.toString()),
        Option(titleText = LocalDate.now().plusYears(19).year.toString()),
        Option(titleText = LocalDate.now().plusYears(20).year.toString()),
    )

    OptionDialog(
        state = selectRandomDate,
        selection = OptionSelection.Single(optionsYears) { _, option ->
            val date = Utils.getRandomDate(option.titleText.toInt())
            viewModel.updateSelectedDate(date)
        },
        header = Header.Custom(
            header = {
                Text(
                    text = "Select year",
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )
            }
        ),
        config = OptionConfig(mode = DisplayMode.GRID_HORIZONTAL)
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = stringResource(R.string.home_screen),
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onNavigateBack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
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
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxWidth()
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

                                Box {
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
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                        .shadow(
                            elevation = 8.dp,
                            ambientColor = MaterialTheme.colorScheme.scrim,
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

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Send to",
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 24.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                TransparentHintTextField(
                    text = email,
                    hint = "Write an email here",
                    onValueChange = {
                        viewModel.updateEmail(it)
                    },
                    textStyle = Typography.bodyLarge,
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Received date",
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 24.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            selectSpecificDate.show()
                        },
                        modifier = Modifier
                            .weight(0.5f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(
                            text = "Select Date",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Button(
                        onClick = {
                            selectRandomDate.show()
                        },
                        modifier = Modifier
                            .weight(0.5f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(
                            text = "Random Date",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isPublic,
                        onCheckedChange = {
                            viewModel.updateIsPublic(it)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )

                    Text(
                        text = "Allow this letter to be made public",
                        style = Typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Text(
                    text = "This letter will appear randomly in the reading section anonymously for other users to read. (Excluding the image)",
                    style = Typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = stringResource(id = R.string.send),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    onClick = {
                        if (viewModel.checkFields()) {
                            viewModel.sendLetter()
                            Utils.showMessage(
                                context,
                                "Yey! Your letter to the future was sent"
                            )
                        } else {
                            Utils.showMessage(
                                context,
                                "Please select date and fill title, letter and email in proper way"
                            )
                        }
                    },
                )
            }
        }
    }
}