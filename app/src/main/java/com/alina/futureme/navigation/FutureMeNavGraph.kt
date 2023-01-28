package com.alina.futureme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alina.futureme.navigation.Screen.*

@Composable
fun FutureMeNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = SignInScreen.route){
    }
}