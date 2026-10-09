package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfileFlow(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    suspend fun getUserProfile(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM brand_progress")
    fun getAllProgressFlow(): Flow<List<BrandProgressEntity>>

    @Query("SELECT * FROM brand_progress WHERE brandId = :brandId LIMIT 1")
    fun getProgressForBrandFlow(brandId: Int): Flow<BrandProgressEntity?>

    @Query("SELECT * FROM brand_progress WHERE brandId = :brandId LIMIT 1")
    suspend fun getProgressForBrand(brandId: Int): BrandProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProgress(progress: BrandProgressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProgress(list: List<BrandProgressEntity>)

    @Query("DELETE FROM brand_progress")
    suspend fun clearAllProgress()
}
