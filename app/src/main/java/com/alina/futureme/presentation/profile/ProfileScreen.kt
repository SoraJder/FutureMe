package com.alina.futureme.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.info.InfoDialog
import com.maxkeppeler.sheets.info.models.InfoBody
import com.maxkeppeler.sheets.info.models.InfoSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colorScheme.tertiaryContainer)

    val infoDialogState = rememberUseCaseState()

    InfoDialog(
        state = infoDialogState, selection = InfoSelection(onPositiveClick = {
            authenticationViewModel.onNavigateSignOutButtonClicked()
            authenticationViewModel.removeUser()
            infoDialogState.finish()
        }, onNegativeClick = {
            infoDialogState.finish()
        }), body = InfoBody.Default(
            bodyText = stringResource(R.string.attention_delete_account)
        ), header = Header.Default(
            title = stringResource(R.string.delete_account)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {
        ProfileSurface(userName = authenticationViewModel.currentUser?.displayName!!)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.your_details),
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 32.dp))

        Text(
            text = stringResource(R.string.display_name),
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = authenticationViewModel.currentUser?.displayName!!,
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.email_text_field),
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = authenticationViewModel.currentUser?.email!!,
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.your_letters),
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 32.dp))

        Button(
            onClick = {
                profileViewModel.onNavigateToSeeYourLetters()
            },
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.Start)
                .wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = stringResource(R.string.see_letters),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Image(
            painter = painterResource(id = R.drawable.paper_plane),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentSize()
        )

        Button(
            onClick = {
                authenticationViewModel.onNavigateSignOutButtonClicked()
                authenticationViewModel.signOut()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Sign Out"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(horizontal = 32.dp),
            onClick = {
                infoDialogState.show()
            }) {
            Text(
                text = "Delete account"
            )
        }
    }
}

@Composable
fun ProfileSurface(
    userName: String
) {
    Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = Typography.displayMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Text(
                text = stringResource(R.string.hi) + " " + userName,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "This is your profile screen.\nCheck your latest letters!",
                style = Typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}