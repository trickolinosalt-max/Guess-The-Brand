package com.example.model

import androidx.annotation.DrawableRes

data class BrandItem(
    val id: Int,
    val name: String,
    val category: String,
    @DrawableRes val logoRes: Int,
    val difficulty: Difficulty = Difficulty.EASY,
    val funFact: String,
    val levelNumber: Int = 1,
    val hint: String = ""
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}
