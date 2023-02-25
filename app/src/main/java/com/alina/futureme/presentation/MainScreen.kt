package com.alina.futureme.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alina.futureme.common.Constants.HOME_SCREEN
import com.alina.futureme.common.Constants.SIGN_IN_SCREEN
import com.alina.futureme.navigation.FutureMeNavHost
import com.alina.futureme.navigation.Screen
import com.alina.futureme.presentation.theme.FutureMeTheme

@Composable
fun MainScreen() {
    val bottomBarState = rememberSaveable { mutableStateOf(true) }

    FutureMeTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        /*when (navBackStackEntry?.destination?.route) {
            HOME_SCREEN -> {
                // bottomBarState.value = true
            }
            SIGN_IN_SCREEN -> {
                //bottomBarState.value = false
            }
        }*/

        //TODO o variabila startDestination in care se verifica daca utilizatorul este logat sau nu
        Scaffold {innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                FutureMeNavHost(navController =navController , startDestination = Screen.SignUpScreen.route)
            }
        }
    }
}

//TODO bottom bar implementat mai jos, in viitor o poti pune la componente comune
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
