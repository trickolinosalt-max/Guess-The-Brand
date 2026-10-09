package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val username: String = "BrandMaster",
    val coins: Int = 120,
    val totalScore: Int = 0,
    val streak: Int = 1,
    val lastDailyDate: String = "",
    val hintsUsed: Int = 0,
    val cloudBackupCode: String = ""
)
