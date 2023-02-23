package com.alina.futureme.navigation

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }
}

sealed class DestinationWithoutArguments(route: String) :Destination(route){
    operator fun invoke(): String = route
}

//get full Route
internal fun String.appendParams(vararg params: Pair<String,Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { argument ->
            builder.append("/$argument")
        }
    }

    return builder.toString()
}