package com.rejzerek.stickmanwarriors.feature_stickman.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import kotlinx.coroutines.flow.Flow

@Dao
interface StickmanDao {
    @Query("SELECT * FROM stickman")
    fun getStickmen(): Flow<List<Stickman>>

    @Query("SELECT * FROM stickman WHERE id = :id")
    suspend fun getStickmanById(id: Int): Stickman?

    @Query("DELETE FROM stickman")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStickman(stickman: Stickman)

    @Delete
    suspend fun deleteStickman(stickman: Stickman)

    @Update
    suspend fun updateStickman(stickman: Stickman)
}