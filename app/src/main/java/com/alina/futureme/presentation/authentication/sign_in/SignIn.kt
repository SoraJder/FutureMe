package com.alina.futureme.presentation.authentication.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alina.futureme.R
import com.alina.futureme.common.Resource
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.navigation.Screen
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun SignIn(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController,
    showErrorMessage: (errorMessage: String?) -> Unit
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
                    LaunchedEffect(Unit) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.SignInScreen.route) { inclusive = true }
                        }
                    }
                } else{
                   showErrorMessage(stringResource(R.string.email_was_not_confirmed))
                }
            }
            null -> Unit
        }
    }
}