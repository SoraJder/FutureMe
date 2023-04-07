package com.alina.futureme.presentation.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.presentation.authentication.AuthenticationViewModel

@Composable
fun ProfileScreen(
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    Text(text = "Profile Screen")
    Button(
        onClick = {
            authenticationViewModel.onNavigateSignOutButtonClicked()
            authenticationViewModel.signOut()
        },
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Sign Out")
    }
}