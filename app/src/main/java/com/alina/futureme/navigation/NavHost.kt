package com.alina.futureme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alina.futureme.presentation.home.HomeScreen
import com.alina.futureme.presentation.authentication.sign_in.SignInScreen
import com.alina.futureme.presentation.authentication.sign_up.SignUpScreen

@Composable
fun FutureMeNavHost(navController: NavHostController, startDestination:String){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.SignInScreen.route){
            SignInScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }
    }
}