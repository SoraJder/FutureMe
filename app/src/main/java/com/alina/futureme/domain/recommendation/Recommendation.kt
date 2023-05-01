package com.alina.futureme.domain.recommendation

import com.alina.futureme.domain.model.PopularLetter

interface Recommendation {

    fun makePopularSore()

    fun makeRecentScore()

    fun combineScore()

    fun getRecommendations(): List<PopularLetter?>
}