package com.alina.futureme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun FutureMeNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = FutureMeDestinations.Home.route){
        composable(route = FutureMeDestinations.Home.route){

        }
    }
}