package com.alina.futureme.domain.model

data class User(
    var email: String="",
    var name: String="",
    var country: String="",
    var birthDate: String="",
    var phoneNumber: String="",
    var lettersReceived: List<String> = emptyList(),
    var likedLetters: List<String> = emptyList()
)

fun User.asMap(): Map<String, Any?> = mapOf(
    "email" to email,
    "name" to name,
    "country" to country,
    "birthDate" to birthDate,
    "phoneNumber" to phoneNumber,
    "lettersReceived" to lettersReceived,
    "likedLetters" to likedLetters
)