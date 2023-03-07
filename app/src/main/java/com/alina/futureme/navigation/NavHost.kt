package com.alina.futureme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alina.futureme.presentation.authentication.forgot_password.ForgotPasswordScreen
import com.alina.futureme.presentation.authentication.sign_in.SignInScreen
import com.alina.futureme.presentation.authentication.sign_up.SignUpScreen
import com.alina.futureme.presentation.authentication.verify_email.VerifyEmailScreen
import com.alina.futureme.presentation.home.HomeScreen

@Composable
fun FutureMeNavHost(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(Screen.VerifyEmailScreen.route){
            VerifyEmailScreen(navController = navController)
        }
    }
}