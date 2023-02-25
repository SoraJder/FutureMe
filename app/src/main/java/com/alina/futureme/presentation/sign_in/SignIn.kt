package com.alina.futureme.presentation.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.common.Resource
import com.alina.futureme.components.ProgressBar

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> Unit
        is Resource.Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                showErrorMessage("It seems that you don't have an account")
            }
        }
    }
}