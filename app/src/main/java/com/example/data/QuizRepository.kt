package com.example.data

import android.util.Base64
import com.example.model.BrandCatalog
import com.example.model.LeaderboardEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QuizRepository(private val dao: QuizDao) {

    val userProfileFlow: Flow<UserProfileEntity?> = dao.getUserProfileFlow()
    val allProgressFlow: Flow<List<BrandProgressEntity>> = dao.getAllProgressFlow()

    suspend fun ensureProfile(): UserProfileEntity {
        var profile = dao.getUserProfile()
        if (profile == null) {
            profile = UserProfileEntity(
                id = 1,
                username = "BrandMaster",
                coins = 120,
                totalScore = 0,
                streak = 1,
                lastDailyDate = ""
            )
            dao.insertOrUpdateProfile(profile)
        }
        return profile
    }

    suspend fun updateUsername(newName: String) {
        val current = ensureProfile()
        dao.insertOrUpdateProfile(current.copy(username = newName.trim()))
    }

    suspend fun getProgressForBrand(brandId: Int): BrandProgressEntity {
        return dao.getProgressForBrand(brandId) ?: BrandProgressEntity(brandId = brandId)
    }

    suspend fun markBrandSolved(brandId: Int, scoreReward: Int = 100, coinReward: Int = 15) {
        val current = dao.getProgressForBrand(brandId) ?: BrandProgressEntity(brandId = brandId)
        if (!current.isSolved) {
            dao.insertOrUpdateProgress(
                current.copy(
                    isSolved = true,
                    solvedAt = System.currentTimeMillis()
                )
            )
            val profile = ensureProfile()
            dao.insertOrUpdateProfile(
                profile.copy(
                    coins = profile.coins + coinReward,
                    totalScore = profile.totalScore + scoreReward
                )
            )
        }
    }

    suspend fun revealLetter(brandId: Int, indexToReveal: Int, cost: Int = 15): Boolean {
        val profile = ensureProfile()
        if (profile.coins < cost) return false

        val current = dao.getProgressForBrand(brandId) ?: BrandProgressEntity(brandId = brandId)
        val existingIndices = if (current.revealedIndices.isBlank()) {
            mutableSetOf()
        } else {
            current.revealedIndices.split(",").mapNotNull { it.toIntOrNull() }.toMutableSet()
        }

        existingIndices.add(indexToReveal)
        dao.insertOrUpdateProgress(
            current.copy(revealedIndices = existingIndices.joinToString(","))
        )
        dao.insertOrUpdateProfile(
            profile.copy(
                coins = profile.coins - cost,
                hintsUsed = profile.hintsUsed + 1
            )
        )
        return true
    }

    suspend fun removeDecoyLetters(brandId: Int, lettersToRemove: List<Char>, cost: Int = 25): Boolean {
        val profile = ensureProfile()
        if (profile.coins < cost) return false

        val current = dao.getProgressForBrand(brandId) ?: BrandProgressEntity(brandId = brandId)
        val existingLetters = if (current.removedLetters.isBlank()) {
            mutableSetOf()
        } else {
            current.removedLetters.split(",").toMutableSet()
        }

        existingLetters.addAll(lettersToRemove.map { it.toString() })
        dao.insertOrUpdateProgress(
            current.copy(removedLetters = existingLetters.joinToString(","))
        )
        dao.insertOrUpdateProfile(
            profile.copy(
                coins = profile.coins - cost,
                hintsUsed = profile.hintsUsed + 1
            )
        )
        return true
    }

    suspend fun solveBrandInstantly(brandId: Int, cost: Int = 50): Boolean {
        val profile = ensureProfile()
        if (profile.coins < cost) return false

        markBrandSolved(brandId, scoreReward = 80, coinReward = 0)
        dao.insertOrUpdateProfile(
            profile.copy(
                coins = profile.coins - cost,
                hintsUsed = profile.hintsUsed + 1
            )
        )
        return true
    }

    suspend fun completeDailyChallenge(brandId: Int): Pair<Int, Int> {
        val todayStr = getTodayDateKey()
        val profile = ensureProfile()

        val isConsecutive = isYesterday(profile.lastDailyDate)
        val newStreak = if (isConsecutive) profile.streak + 1 else if (profile.lastDailyDate == todayStr) profile.streak else 1

        val bonusCoins = 30 + (newStreak * 5)
        val bonusScore = 250 + (newStreak * 20)

        markBrandSolved(brandId, scoreReward = bonusScore, coinReward = bonusCoins)
        val updatedProfile = ensureProfile()
        dao.insertOrUpdateProfile(
            updatedProfile.copy(
                streak = newStreak,
                lastDailyDate = todayStr
            )
        )
        return Pair(bonusCoins, newStreak)
    }

    fun getTodayDateKey(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    private fun isYesterday(dateStr: String): Boolean {
        if (dateStr.isBlank()) return false
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val prev = sdf.parse(dateStr)?.time ?: return false
            val diff = System.currentTimeMillis() - prev
            val oneDayMs = 24 * 60 * 60 * 1000L
            diff in (12 * 60 * 60 * 1000L)..(36 * 60 * 60 * 1000L)
        } catch (_: Exception) {
            false
        }
    }

    // Cloud Saves: Export backup code
    suspend fun generateCloudSaveCode(): String {
        val profile = ensureProfile()
        val allProgress = dao.getAllProgressFlow().first()
        val solvedIds = allProgress.filter { it.isSolved }.map { it.brandId }

        val json = JSONObject().apply {
            put("version", 1)
            put("username", profile.username)
            put("coins", profile.coins)
            put("score", profile.totalScore)
            put("streak", profile.streak)
            put("hints", profile.hintsUsed)
            put("solved", JSONArray(solvedIds))
            put("timestamp", System.currentTimeMillis())
        }
        val encoded = Base64.encodeToString(json.toString().toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
        return "GTB-SAVE-$encoded"
    }

    // Cloud Saves: Import backup code
    suspend fun restoreFromCloudSave(saveCode: String): Result<String> {
        return try {
            val cleanCode = saveCode.trim()
            val raw = if (cleanCode.startsWith("GTB-SAVE-")) cleanCode.removePrefix("GTB-SAVE-") else cleanCode
            val decoded = String(Base64.decode(raw, Base64.DEFAULT), Charsets.UTF_8)
            val json = JSONObject(decoded)

            val username = json.optString("username", "BrandMaster")
            val coins = json.optInt("coins", 100)
            val score = json.optInt("score", 0)
            val streak = json.optInt("streak", 1)
            val hints = json.optInt("hints", 0)

            val solvedArray = json.optJSONArray("solved") ?: JSONArray()
            val newProgress = mutableListOf<BrandProgressEntity>()
            for (i in 0 until solvedArray.length()) {
                val brandId = solvedArray.getInt(i)
                newProgress.add(BrandProgressEntity(brandId = brandId, isSolved = true))
            }

            dao.insertOrUpdateProfile(
                UserProfileEntity(
                    id = 1,
                    username = username,
                    coins = coins,
                    totalScore = score,
                    streak = streak,
                    hintsUsed = hints
                )
            )
            dao.insertAllProgress(newProgress)
            Result.success("Restored progress for $username! Solved: ${newProgress.size} brands, Coins: $coins")
        } catch (e: Exception) {
            Result.failure(Exception("Invalid Cloud Save Code: ${e.message}"))
        }
    }

    // Global & Friends Leaderboards
    fun getLeaderboards(currentUser: UserProfileEntity?, solvedCount: Int): Pair<List<LeaderboardEntry>, List<LeaderboardEntry>> {
        val myScore = currentUser?.totalScore ?: 0
        val myStreak = currentUser?.streak ?: 1
        val myName = currentUser?.username ?: "You"

        val baseGlobal = listOf(
            LeaderboardEntry("p1", 1, "LogoEmperor_Max", 4200, 15, 14, false, false, "Grandmaster", 0xFFE11D48),
            LeaderboardEntry("p2", 2, "QuizQueen_Sofia", 3850, 15, 11, false, false, "Diamond", 0xFF7C3AED),
            LeaderboardEntry("p3", 3, "TriviaTitan_Dave", 3400, 14, 9, false, false, "Diamond", 0xFF0284C7),
            LeaderboardEntry("p4", 4, "IconicGamer_Ken", 2950, 13, 7, false, false, "Gold", 0xFF059669),
            LeaderboardEntry("p5", 5, "BrandHunter_Mia", 2400, 12, 6, false, false, "Gold", 0xFFD97706),
            LeaderboardEntry("p6", 6, "RetroPixel_Dan", 1950, 10, 5, false, false, "Silver", 0xFF475569),
            LeaderboardEntry("p7", 7, "NovaGuesser_Eli", 1500, 8, 4, false, false, "Silver", 0xFF6366F1),
            LeaderboardEntry("p8", 8, "DailyRival_Alex", 1100, 6, 3, false, false, "Bronze", 0xFF9333EA),
            LeaderboardEntry("p9", 9, "LogoRookie_Sam", 750, 4, 2, false, false, "Bronze", 0xFF0D9488),
            LeaderboardEntry("p10", 10, "SpeedyGamer_Ben", 400, 2, 1, false, false, "Bronze", 0xFFEAB308)
        )

        // Add current user to list and calculate rank
        val currentUserEntry = LeaderboardEntry(
            id = "me",
            rank = 1,
            username = myName,
            score = myScore,
            solvedCount = solvedCount,
            streak = myStreak,
            isFriend = false,
            isCurrentUser = true,
            tier = when {
                myScore >= 3500 -> "Grandmaster"
                myScore >= 2500 -> "Diamond"
                myScore >= 1500 -> "Gold"
                myScore >= 600 -> "Silver"
                else -> "Bronze"
            },
            avatarBgColor = 0xFF2563EB
        )

        val fullGlobal = (baseGlobal + currentUserEntry)
            .sortedByDescending { it.score }
            .mapIndexed { index, entry -> entry.copy(rank = index + 1) }

        // Friends Leaderboard
        val friendsList = listOf(
            LeaderboardEntry("f1", 1, "Chloe_Bestie", 2800, 13, 8, true, false, "Gold", 0xFFEC4899),
            LeaderboardEntry("f2", 2, "Jordan_Dev", 2150, 11, 5, true, false, "Gold", 0xFF3B82F6),
            currentUserEntry.copy(isFriend = true),
            LeaderboardEntry("f3", 4, "Sammy_Quiz", 1300, 7, 3, true, false, "Silver", 0xFF10B981),
            LeaderboardEntry("f4", 5, "Lucas_Gaming", 850, 5, 2, true, false, "Bronze", 0xFFF59E0B)
        ).sortedByDescending { it.score }
            .mapIndexed { index, entry -> entry.copy(rank = index + 1) }

        return Pair(fullGlobal, friendsList)
    }
}
