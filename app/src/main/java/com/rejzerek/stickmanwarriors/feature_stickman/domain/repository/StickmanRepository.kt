package com.rejzerek.stickmanwarriors.feature_stickman.domain.repository

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import kotlinx.coroutines.flow.Flow

interface StickmanRepository {

    fun getStickmen(): Flow<List<Stickman>>
    suspend fun getStickmanById(id: Int): Stickman?

    suspend fun insertStickman(stickman: Stickman)

    suspend fun deleteStickman(stickman: Stickman)

    suspend fun updateStickman(stickman: Stickman)

}