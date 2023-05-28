package com.alina.futureme.presentation.letters.send_instant_letter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.components.TransparentHintTextField
import com.alina.futureme.presentation.theme.Typography
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendInstantLetterScreen(
    viewModel: SendInstantLetterViewModel = hiltViewModel()
) {
    val focusRequester = remember { FocusRequester() }
    val optionState = rememberUseCaseState()

    val context = LocalContext.current

    val subject by viewModel.subject
    val to by viewModel.to
    val letterText by viewModel.text

    val options = listOf(
        Option(
            IconSource(R.drawable.cake),
            titleText = "Birthday"
        ),
        Option(
            IconSource(R.drawable.parent),
            titleText = "Parents"
        ),
        Option(
            IconSource(R.drawable.heart),
            titleText = "Lover"
        )
    )

    val optionsTemplateEmail =
        listOf(TemplateEmail.Birthday, TemplateEmail.Parents, TemplateEmail.Lover)

    OptionDialog(
        state = optionState,
        selection = OptionSelection.Single(options) { index, _ ->
            val templateEmail = optionsTemplateEmail[index]
            viewModel.updateSubject(templateEmail.subject)
            viewModel.updateText(templateEmail.text)
            optionState.finish()
        },
        config = OptionConfig(mode = DisplayMode.LIST)
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
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
        },
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
            ) {
                TextButton(
                    onClick = {
                        optionState.show()
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.dont_have_any_idea),
                        letterSpacing = 1.sp,
                        style = Typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Subject: ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    TransparentHintTextField(
                        text = subject,
                        hint = "",
                        onValueChange = {
                            viewModel.updateSubject(it)
                        },
                        textStyle = Typography.bodyMedium,
                        singleLine = true,
                        modifier = Modifier.padding(end = 24.dp)
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp))

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "To: ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    TransparentHintTextField(
                        text = to,
                        hint = "",
                        onValueChange = {
                            viewModel.updateTo(it)
                        },
                        textStyle = Typography.bodyMedium,
                        singleLine = true,
                        modifier = Modifier.padding(end = 24.dp)
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp))
                Spacer(modifier = Modifier.height(8.dp))

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
                        hint = "Hi...",
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
                        viewModel.sendEmail(context)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}