package com.alina.futureme.data.recommendation

import com.alina.common.Resource
import com.alina.futureme.domain.model.ShowLetter
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.ln

object Recommendation {

    private fun calculateScore(showLetter: List<ShowLetter>) {
        showLetter.forEach { letter ->
            letter.let {
                it.popularScore = ln(it.numberOfLikes.toDouble() + 1)

                val current = LocalDate.now()
                val dateToArrive = LocalDate.parse(it.dateToArrive)
                val daysAgo = ChronoUnit.DAYS.between(current, dateToArrive)

                it.recentScore = if (daysAgo == 0L) {
                    1.0
                } else {
                    1.0 / daysAgo
                }

                it.score = it.popularScore * 0.7 + it.recentScore * 0.3
            }
        }
    }

    fun getRecommendations(showLetter: List<ShowLetter>): Resource<List<ShowLetter>> {
        return try {
            calculateScore(showLetter)
            val sortedList = showLetter.sortedByDescending { it.score }
            Resource.Success(sortedList)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    fun getRecent(showLetter: List<ShowLetter>): Resource<List<ShowLetter>> {
        return try {
            val sortedList = showLetter.sortedByDescending { it.dateToArrive }
            Resource.Success(sortedList)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}