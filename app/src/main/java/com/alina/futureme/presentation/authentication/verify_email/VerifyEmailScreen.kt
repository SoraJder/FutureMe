package com.alina.futureme.presentation.authentication.verify_email

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.components.PrimaryButton
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography

@Composable
fun VerifyEmailScreen(
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.email_confirmation),
                style = Typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.nice_to_meet_you),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.ready_to_start_verify_email),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.verifiy_spam_folder),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                text = stringResource(R.string.go_to_sign_in),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                onClick = { viewModel.onNavigateToSignInButtonClicked() }
            )
        }
    }
}