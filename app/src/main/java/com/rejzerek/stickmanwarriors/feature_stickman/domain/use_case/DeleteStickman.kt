package com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.repository.StickmanRepository

class DeleteStickman(
    private val repository: StickmanRepository
){
    suspend operator fun invoke(stickman: Stickman){
        repository.deleteStickman(stickman)
    }
}