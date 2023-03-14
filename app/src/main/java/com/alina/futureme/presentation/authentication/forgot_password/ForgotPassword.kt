package com.alina.futureme.presentation.authentication.forgot_password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alina.futureme.common.Resource
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.navigation.Screen
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun ForgotPassword(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController,
    showSuccessMessage: (successMessage: String?) -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    val forgotPasswordFlow = viewModel.forgotPasswordFlow.collectAsState()

    forgotPasswordFlow.value.let {
        when (it) {
            is Resource.Failure -> {
                showErrorMessage(it.e.message)
            }
            is Resource.Loading -> ProgressBar()
            is Resource.Success -> {
                val isEmailSend = it.data
                if (isEmailSend) {
                    LaunchedEffect(Unit) {
                        showSuccessMessage("Check the email to reset password")
                        navController.popBackStack(Screen.SignInScreen.route, false)
                    }
                }
            }
        }
    }
}