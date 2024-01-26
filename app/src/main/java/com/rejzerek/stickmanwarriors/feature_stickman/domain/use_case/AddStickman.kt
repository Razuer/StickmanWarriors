package com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.InvalidStickmanException
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.repository.StickmanRepository

class AddStickman(
    private val repository: StickmanRepository
){
    @Throws(InvalidStickmanException::class)
    suspend operator fun invoke(stickman: Stickman){
        if(stickman.name.isBlank()){
            throw InvalidStickmanException("The name of the stickman can't be empty.")
        }
        if(stickman.health <= 0){
            throw InvalidStickmanException("Health must be greater than 0.")
        }
        if(stickman.attackDamage <= 0){
            throw InvalidStickmanException("Attack damage must be greater than 0.")
        }
        if(stickman.defense < 0){
            throw InvalidStickmanException("Defense must be greater than or equal to 0.")
        }
        if(stickman.weapon.isBlank()){
            throw InvalidStickmanException("The stickman must have a weapon.")
        }
        repository.insertStickman(stickman)
    }
}