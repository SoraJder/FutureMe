package com.alina.futureme.presentation.authentication.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.common.Utils
import com.alina.futureme.components.CustomTextField
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography

@Composable
fun SignUpScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    var nameText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var confirmPasswordText by rememberSaveable { mutableStateOf("") }

    var validateNameText by rememberSaveable { mutableStateOf(true) }
    var validateEmailText by rememberSaveable { mutableStateOf(true) }
    var validatePasswordText by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPasswordText by rememberSaveable { mutableStateOf(true) }
    var validatePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordsVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordsEqual by rememberSaveable { mutableStateOf(false) }

    fun validateData(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val passwordRegex = Utils.isPasswordValid(password)
        val confirmPasswordRegex = Utils.isPasswordValid(confirmPassword)
        val emailRegex = Utils.isEmailValid(email)

        validateNameText = name.isNotBlank()
        validateEmailText = emailRegex
        validatePasswordText = passwordRegex
        validateConfirmPasswordText = confirmPasswordRegex
        validatePasswordsEqual = password == confirmPassword

        return validateNameText && validateEmailText && validatePasswordText
                && validateConfirmPasswordText && validatePasswordsEqual
    }

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
                ), horizontalAlignment = Alignment.CenterHorizontally
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
                text = nameText,
                placeholder = stringResource(R.string.name),
                onValueChange = { nameText = it },
                showError = !validateNameText,
                errorMessage = stringResource(id = R.string.validate_name_error)
            )

            Spacer(modifier = Modifier.height(4.dp))
            CustomTextField(
                text = emailText,
                placeholder = stringResource(id = R.string.email_text_field),
                onValueChange = { emailText = it },
                showError = !validateEmailText,
                errorMessage = stringResource(id = R.string.validate_email_error)
            )

            Spacer(modifier = Modifier.height(4.dp))
            CustomTextField(
                text = passwordText,
                placeholder = stringResource(id = R.string.enter_password),
                isPasswordTextField = true,
                isPasswordVisible = isPasswordsVisible,
                onVisibilityChange = { isPasswordsVisible = it },
                showError = !validatePasswordText,
                errorMessage = stringResource(id = R.string.validate_password_error),
                onValueChange = { passwordText = it }
            )

            Spacer(modifier = Modifier.padding(4.dp))
            CustomTextField(
                text = confirmPasswordText,
                placeholder = stringResource(id = R.string.confirm_password),
                isPasswordTextField = true,
                isPasswordVisible = isConfirmPasswordsEqual,
                onVisibilityChange = { isConfirmPasswordsEqual = it },
                onValueChange = { confirmPasswordText = it },
                showError = !validateConfirmPasswordText || !validatePasswordsEqual,
                errorMessage = if (!validateConfirmPasswordText) stringResource(id = R.string.validate_password_error) else stringResource(
                    id = R.string.validate_equal_password_error
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))
            PrimaryButton(
                text = stringResource(R.string.create_account),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = {
                    if (validateData(
                            nameText,
                            emailText,
                            passwordText,
                            confirmPasswordText
                        )
                    ) {
                        viewModel.signUp(emailText, passwordText,nameText)
                    }
                },
            )

            Text(
                text = stringResource(R.string.joined_before_question),
                color= MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 15.dp)
            )
            TextButton(
                onClick = { viewModel.onNavigateToSignInButtonClicked() },
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    letterSpacing = 1.sp,
                    style = Typography.labelLarge
                )
            }
        }
    }

    SignUp(name = nameText) { message ->
        Utils.showMessage(context, message)
    }
}