package com.alina.futureme.presentation.authentication.forgot_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.common.Utils
import com.alina.futureme.common.Utils.showMessage
import com.alina.futureme.components.CustomTextField
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography

@Composable
fun ForgotPasswordScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    var emailText by rememberSaveable { mutableStateOf("") }
    val validateEmailText by rememberSaveable { mutableStateOf(true) }

    fun validateEmail(email: String) = Utils.isEmailValid(email)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_futureme),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.forgot_password),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(top = 32.dp, start = 40.dp)
                    .fillMaxWidth(),
                style = Typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(id = R.string.forgot_password_additional_text),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(start = 40.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                text = emailText,
                placeholder = stringResource(id = R.string.email_text_field),
                isPasswordTextField = false,
                onValueChange = { emailText = it },
                showError = !validateEmailText,
                errorMessage = stringResource(id = R.string.validate_email_error)
            )

            Spacer(modifier = Modifier.padding(10.dp))
            PrimaryButton(
                text = stringResource(id = R.string.submit),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = {
                    if (validateEmail(emailText)) {
                        viewModel.forgotPassword(emailText)
                    }
                },
            )
            Spacer(modifier = Modifier.padding(60.dp))
        }
    }

    ForgotPassword(
        showSuccessMessage = { successMessage ->
            showMessage(context, successMessage)
        },
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        })
}