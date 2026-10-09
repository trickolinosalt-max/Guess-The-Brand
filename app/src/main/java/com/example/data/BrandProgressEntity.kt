package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand_progress")
data class BrandProgressEntity(
    @PrimaryKey val brandId: Int,
    val isSolved: Boolean = false,
    val revealedIndices: String = "", // comma-separated e.g. "0,2"
    val removedLetters: String = "",  // comma-separated removed decoys
    val solvedAt: Long = 0L
)
