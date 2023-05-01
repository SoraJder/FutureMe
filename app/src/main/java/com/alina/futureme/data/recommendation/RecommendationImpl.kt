package com.alina.futureme.data.recommendation

import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.domain.model.PopularLetter
import com.alina.futureme.domain.model.toPopularLetter
import com.alina.futureme.domain.recommendation.Recommendation
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.ln

class RecommendationImpl @Inject constructor(
    letterRepository: LetterRepository
) : Recommendation {

    private val popularLetter: List<PopularLetter?> = runBlocking {
        letterRepository.getPublicLetters().map {
            it?.toPopularLetter()
        }
    }

    override fun makePopularSore() {
        popularLetter.forEach { letter ->
            letter?.let {
                it.popularScore = ln(it.numberOfLikes.toDouble() + 1)
            }
        }
    }

    override fun makeRecentScore() {
        popularLetter.forEach { letter ->
            letter?.let {
                val current = LocalDate.now()
                val dateToArrive = LocalDate.parse(it.dateToArrive)
                val daysAgo = ChronoUnit.DAYS.between(current, dateToArrive)

                it.recentScore = if (daysAgo == 0L) {
                    1.0
                } else {
                    1.0 / daysAgo
                }
            }
        }
    }

    override fun combineScore() {
        makeRecentScore()
        makePopularSore()
        popularLetter.forEach { letter ->
            letter?.let {
                it.score = it.popularScore * 0.7 + it.recentScore * 0.3
            }
        }
    }

    override fun getRecommendations(): List<PopularLetter?> {
        combineScore()
        popularLetter.sortedByDescending { it?.score }
        return popularLetter
    }
}