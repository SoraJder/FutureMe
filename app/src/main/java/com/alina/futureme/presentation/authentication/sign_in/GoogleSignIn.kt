package com.alina.futureme.presentation.authentication.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.common.Resource
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun GoogleSignIn(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit,
) {
    val googleSignInFlow = viewModel.googleSignInFlow.collectAsState()
    val userExistFlow = viewModel.userExistFlow.collectAsState()

    googleSignInFlow.value.let {
        when (it) {
            is Resource.Failure -> {
                showErrorMessage(it.e.message)
            }

            Resource.Loading -> ProgressBar()
            is Resource.Success -> {
                val currentUser = viewModel.currentUser

                currentUser?.email?.let { email ->
                    viewModel.userExistsInFirestore(email)

                    userExistFlow.value.let { userExist ->
                        when (userExist) {
                            false -> {
                                viewModel.createUser(currentUser.displayName!!)
                            }

                            else -> Unit
                        }
                        viewModel.onNavigateToOnboardScreen()
                    }
                }
            }

            null -> Unit
        }
    }
}