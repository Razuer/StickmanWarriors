package com.rejzerek.stickmanwarriors.feature_stickman.data.repository

import com.rejzerek.stickmanwarriors.feature_stickman.data.data_source.StickmanDao
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.repository.StickmanRepository
import kotlinx.coroutines.flow.Flow

class StickmanRepositoryImpl(
    private val dao : StickmanDao
) : StickmanRepository {
    override fun getStickmen(): Flow<List<Stickman>> {
        return dao.getStickmen()
    }

    override suspend fun getStickmanById(id: Int): Stickman? {
        return dao.getStickmanById(id)
    }

    override suspend fun insertStickman(stickman: Stickman) {
        dao.insertStickman(stickman)
    }

    override suspend fun deleteStickman(stickman: Stickman) {
        dao.deleteStickman(stickman)
    }

    override suspend fun updateStickman(stickman: Stickman) {
        dao.updateStickman(stickman)
    }
}