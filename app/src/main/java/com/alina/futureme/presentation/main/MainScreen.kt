package com.alina.futureme.presentation.main

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.alina.futureme.navigation.*
import com.alina.futureme.presentation.authentication.forgot_password.ForgotPasswordScreen
import com.alina.futureme.presentation.authentication.sign_in.SignInScreen
import com.alina.futureme.presentation.authentication.sign_up.SignUpScreen
import com.alina.futureme.presentation.authentication.verify_email.VerifyEmailScreen
import com.alina.futureme.presentation.home.HomeScreen
import com.alina.futureme.presentation.letters.read_letters.ReadLetterScreen
import com.alina.futureme.presentation.letters.send_instant_letter.SendInstantLetterScreen
import com.alina.futureme.presentation.letters.write_letter.WriteLetterScreen
import com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips.BottomSheetIdeasScreen
import com.alina.futureme.presentation.onboarding.OnboardScreen
import com.alina.futureme.presentation.profile.ProfileScreen
import com.alina.futureme.presentation.theme.FutureMeTheme
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    startDestinationScreen: Destination,
) {
    val navController = rememberNavController()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = false
    )
    val bottomSheetNavigator = remember { BottomSheetNavigator(sheetState) }
    navController.navigatorProvider += bottomSheetNavigator

    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController
    )
    FutureMeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                FutureMeNavHost(
                    navController = navController,
                    startDestination = startDestinationScreen
                ) {
                    composable(destination = Destination.HomeScreen) {
                        HomeScreen()
                    }
                    composable(destination = Destination.SignInScreen) {
                        SignInScreen()
                    }
                    composable(destination = Destination.SignUpScreen) {
                        SignUpScreen()
                    }
                    composable(destination = Destination.VerifyEmailScreen) {
                        VerifyEmailScreen()
                    }
                    composable(destination = Destination.ForgotPasswordScreen) {
                        ForgotPasswordScreen()
                    }
                    composable(destination = Destination.OnboardScreen) {
                        OnboardScreen()
                    }
                    composable(destination = Destination.ProfileScreen) {
                        ProfileScreen()
                    }
                    composable(destination = Destination.ReadLetterScreen) {
                        ReadLetterScreen()
                    }
                    composable(destination = Destination.WriteLetterScreen) {
                        WriteLetterScreen()
                    }
                    composable(destination = Destination.SendInstantLetterScreen) {
                        SendInstantLetterScreen()
                    }
                    bottomSheet(destination = Destination.BottomSheetIdeasScreen) {
                        BottomSheetIdeasScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}