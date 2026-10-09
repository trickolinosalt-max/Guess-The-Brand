package com.example.model

data class LeaderboardEntry(
    val id: String,
    val rank: Int,
    val username: String,
    val score: Int,
    val solvedCount: Int,
    val streak: Int,
    val isFriend: Boolean = false,
    val isCurrentUser: Boolean = false,
    val tier: String = "Gold",
    val avatarBgColor: Long = 0xFF2563EB
)
