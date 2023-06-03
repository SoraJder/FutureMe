package com.alina.futureme.presentation.authentication.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.common.Resource
import com.alina.futureme.R
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun SignUp(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    name: String,
    showMessage: (message: String?) -> Unit,
) {
    val signUpFlow = viewModel.signUpFlow.collectAsState()

    signUpFlow.value.let {
        when (it) {
            is Resource.Failure -> {
                showMessage(it.e.message)
            }

            is Resource.Loading -> ProgressBar()
            is Resource.Success -> {
                viewModel.createUser(name)
                viewModel.sendEmailVerification()
                viewModel.onNavigateToVerifyEmail()
                showMessage(stringResource(R.string.email_verification_was_sent))
            }

            null -> Unit
        }
    }
}