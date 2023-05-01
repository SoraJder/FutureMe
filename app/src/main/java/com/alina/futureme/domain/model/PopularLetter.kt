package com.alina.futureme.domain.model

data class PopularLetter(
    val id: String,
    val title: String,
    val text: String,
    val image: String?,
    val numberOfLikes:Int,
    val dateToArrive: String,
    val dateWasSend: String,
    var score: Double,
    var popularScore:Double,
    var recentScore:Double
)
