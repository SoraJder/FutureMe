package com.alina.futureme.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun HomeScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home",
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentHeight()
        )
        Button(
            onClick = {
                viewModel.signOut()
                viewModel.onNavigateToSignInButtonClicked()
            },
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign Out")
        }
    }
}