package com.alina.futureme.presentation.authentication.sign_in

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.common.Constants.WEB_CLIENT_ID
import com.alina.common.Utils
import com.alina.common.Utils.showMessage
import com.alina.futureme.R
import com.alina.futureme.components.CustomTextField
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.components.PrimaryButtonWithContent
import com.alina.futureme.components.TextWithLinesOnSides
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

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

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

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
                    style = Typography.labelLarge
                )
            }

            PrimaryButton(
                text = stringResource(id = R.string.sign_in),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = {
                    if (validateData(emailText, passwordText)) {
                        viewModel.signIn(emailText, passwordText)
                    }
                },
            )
            TextWithLinesOnSides(stringResource(id = R.string.or_between_lines))
            PrimaryButtonWithContent(
                text = stringResource(id = R.string.sign_in_with_google),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(WEB_CLIENT_ID)
                        .requestEmail()
                        .build()

                    val googleSingInClient = GoogleSignIn.getClient(context, gso)

                    launcher.launch(googleSingInClient.signInIntent)
                },
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
                modifier = Modifier.padding(top = 15.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            TextButton(
                onClick = { viewModel.onNavigateToSignUpButtonClicked() },
            ) {
                Text(
                    text = stringResource(R.string.create_account),
                    letterSpacing = 1.sp,
                    style = Typography.labelLarge
                )
            }
        }
    }

    SignIn { errorMessage ->
        showMessage(context, errorMessage)
    }

    GoogleSignIn { errorMessage ->
        showMessage(context, errorMessage)
    }
}