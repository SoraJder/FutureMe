package com.alina.futureme.presentation.authentication.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.common.Resource
import com.alina.futureme.R
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun SignIn(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit,
) {
    val signInFlow = viewModel.signInFlow.collectAsState()

    signInFlow.value.let {
        when (it) {
            is Resource.Failure -> {
                showErrorMessage(it.e.message)
            }

            is Resource.Loading -> ProgressBar()
            is Resource.Success -> {
                if (viewModel.isEmailVerified() == true) {
                    viewModel.onNavigateToOnboardScreen()
                } else {
                    showErrorMessage(stringResource(R.string.email_was_not_confirmed))
                    viewModel.sendEmailVerification()
                }
            }

            null -> Unit
        }
    }
}