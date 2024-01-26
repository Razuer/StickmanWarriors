package com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.repository.StickmanRepository

class GetStickman (
    private val repository: StickmanRepository
){
    suspend operator fun invoke(id: Int): Stickman? {
        return repository.getStickmanById(id)
    }
}