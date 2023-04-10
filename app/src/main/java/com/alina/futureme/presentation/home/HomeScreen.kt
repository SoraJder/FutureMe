package com.alina.futureme.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.R
import com.alina.futureme.components.FAQButton
import com.alina.futureme.components.ProfileButton
import com.alina.futureme.presentation.authentication.AuthenticationViewModel
import com.alina.futureme.presentation.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.tertiaryContainer)

    val userName = authenticationViewModel.currentUser?.displayName!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        HomeCard(
            userName = userName,
            onProfileClick = {
                homeViewModel.onNavigateToProfile()
            }
        )

        ButtonCard(
            image = R.drawable.note,
            text = "Write Letter"
        ) {
            homeViewModel.onNavigateToWriteLetter()
        }

        ButtonCard(
            image = R.drawable.reading_book,
            text = "Read Letter",
            onClick = { homeViewModel.onNavigateToReadLetter() }
        )
        ButtonCard(
            image = R.drawable.message,
            text = "Send Instant Letter"
        ) {
            homeViewModel.onNavigateToSendInstantLetter()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonCard(
    @DrawableRes
    image: Int,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 32.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = MaterialTheme.colorScheme.scrim,
                shape = RoundedCornerShape(10.dp)
            ),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = text,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun HomeCard(
    userName: String,
    onProfileClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = Typography.displayMedium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                FAQButton {
                    //TODO deschidere dialog alert somehow
                }
                Spacer(modifier = Modifier.width(16.dp))

                ProfileButton(onProfileClick)
                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(
                text = stringResource(R.string.hi) + " " + userName,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "What is your message for future?",
                style = Typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}