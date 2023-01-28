package com.alina.futureme.navigation

interface FutureMeDestination {
    val route: String
}

object FutureMeDestinations{
    object Home: FutureMeDestination{
        override val route: String = "home"
    }
}