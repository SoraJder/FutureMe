package com.alina.futureme.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alina.futureme.common.Constants.HOME_SCREEN
import com.alina.futureme.common.Constants.SIGN_IN_SCREEN
import com.alina.futureme.navigation.Screen
import com.alina.futureme.presentation.theme.FutureMeTheme

@Composable
fun MainScreen(){
    val bottomBarState = rememberSaveable{ mutableStateOf(true)}

    FutureMeTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when(navBackStackEntry?.destination?.route){
           HOME_SCREEN -> {
              // bottomBarState.value = true
           }
            SIGN_IN_SCREEN ->{
                //bottomBarState.value = false
            }
        }

        Scaffold {
                NavHost(
                    navController = navController,
                    modifier =Modifier.padding(it),
                    startDestination = Screen.Home.route ){
                    composable(Screen.Home.route){
                        HomeScreen(navController)
                    }
                    composable(Screen.SignInScreen.route){
                        SignInScreen(navController)
                    }
                }
            }

    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Box(contentAlignment = Alignment.Center){
        Column {
            Text(text = "HOME", textAlign = TextAlign.Center)
            Button(
                onClick = {navController.navigate(SIGN_IN_SCREEN)},
                content = {Text(text = "Go to sign in")}
            )
        }
    }
}

@Composable
fun SignInScreen(navController: NavController) {
    Box(contentAlignment = Alignment.Center){
        Column {
            Text(text = "SIGN IN", textAlign = TextAlign.Center)
            Button(
                onClick = {navController.navigate(HOME_SCREEN)},
                content = {Text(text = "Go to home")}
            )
        }

    }
}
/*@Composable
fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val items = listOf(
        NavigationItem.Cars,
        NavigationItem.Bikes,
        NavigationItem.Settings
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    )
}*/
