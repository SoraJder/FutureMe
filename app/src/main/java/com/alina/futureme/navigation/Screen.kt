package com.alina.futureme.navigation

import com.alina.futureme.common.Constants.HOME_SCREEN
import com.alina.futureme.common.Constants.SIGN_IN_SCREEN
import com.alina.futureme.common.Constants.SIGN_UP_SCREEN

sealed class Screen (val route: String){
    object SignInScreen : Screen(SIGN_IN_SCREEN)
    object SignUPScreen: Screen(SIGN_UP_SCREEN)
    object HOME :Screen(HOME_SCREEN)
}