package com.alina.futureme.presentation.authentication.sign_up

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
fun SignUp(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    val signUpFlow = viewModel.signUpFlow.collectAsState()

    signUpFlow.value.let {
        when (it) {
            is Resource.Failure -> {
                showErrorMessage(it.e.message)
            }
            is Resource.Loading -> ProgressBar()
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.VerifyEmailScreen.route)
                }
                viewModel.sendEmailVerification()
                viewModel.currentUser
            }
            null -> Unit
        }
    }
}