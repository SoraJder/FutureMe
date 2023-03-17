package com.alina.futureme.presentation.authentication.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.common.Utils
import com.alina.futureme.common.Utils.showMessage
import com.alina.futureme.components.CustomTextField
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.components.PrimaryButtonWithContent
import com.alina.futureme.components.TextWithLinesOnSides
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography

@Composable
fun SignInScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var isPasswordsVisible by rememberSaveable { mutableStateOf(false) }

    var validateEmailText by rememberSaveable { mutableStateOf(true) }
    var validatePasswordText by rememberSaveable { mutableStateOf(true) }

    fun validateData(
        email: String,
        password: String,
    ): Boolean {
        val passwordRegex = Utils.isPasswordValid(password)
        val emailRegex = Utils.isEmailValid(email)

        validateEmailText = emailRegex
        validatePasswordText = passwordRegex

        return validateEmailText && validatePasswordText
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
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
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(24.dp)
            )
            CustomTextField(
                text = emailText,
                placeholder = stringResource(id = R.string.email_text_field),
                onValueChange = { emailText = it },
                showError = !validateEmailText,
                errorMessage = stringResource(id = R.string.validate_email_error)
            )

            Spacer(modifier = Modifier.padding(4.dp))
            CustomTextField(
                text = passwordText,
                placeholder = stringResource(id = R.string.enter_password),
                isPasswordTextField = true,
                isPasswordVisible = isPasswordsVisible,
                onVisibilityChange = { isPasswordsVisible = it },
                onValueChange = { passwordText = it },
                showError = !validatePasswordText,
                errorMessage = stringResource(id = R.string.validate_password_error)
            )

            TextButton(
                onClick = { viewModel.onNavigateToForgotPasswordButtonClicked() },
                modifier = Modifier
                    .align(
                        alignment = Alignment.End
                    )
                    .padding(horizontal = 16.dp)

            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    letterSpacing = 1.sp,
                    style = Typography.button
                )
            }

            PrimaryButton(
                text = stringResource(id = R.string.sign_in),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = {
                    if (validateData(emailText, passwordText)) {
                        viewModel.signIn(emailText,passwordText)
                    }
                },
            )
            TextWithLinesOnSides(stringResource(id = R.string.or_between_lines))
            PrimaryButtonWithContent(
                text = stringResource(id = R.string.sign_in_with_google),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = { /*TODO*/ },
                {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_google),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .align(alignment = Alignment.Start),
                        tint = Color.Unspecified
                    )
                }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = stringResource(R.string.new_to_futureme),
                modifier = Modifier.padding(top = 15.dp)
            )
            TextButton(
                onClick = { viewModel.onNavigateToSignUpButtonClicked() },
            ) {
                Text(
                    text = stringResource(R.string.create_account),
                    letterSpacing = 1.sp,
                    style = Typography.button
                )
            }
        }
    }

    SignIn { errorMessage ->
        showMessage(context, errorMessage)
    }
}