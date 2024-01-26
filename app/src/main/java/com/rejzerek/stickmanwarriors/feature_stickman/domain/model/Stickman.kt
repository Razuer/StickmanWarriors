package com.rejzerek.stickmanwarriors.feature_stickman.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class StickmanClass {
    WARRIOR,
    MAGE,
    ARCHER
}

@Entity(tableName = "stickman")
data class Stickman(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val `class`: StickmanClass,
    val name: String,
    val health: Int,
    val attackDamage: Int,
    val defense: Int,
    val imageResource: Int,
    val weapon: String
) {
    companion object {
//        val DEFAULT_WARRIOR = Stickman(
//            0,
//            StickmanClass.WARRIOR,
//            "Default Warrior",
//            100,
//            20,
//            10,
//            R.drawable.warrior_img,
//            "Sword"
//        )
//        val DEFAULT_MAGE =
//            Stickman(StickmanClass.MAGE, "Default Mage", 80, 25, 5, R.drawable.mage_img, "Staff")
//        val DEFAULT_ARCHER = Stickman(
//            0,
//            StickmanClass.ARCHER,
//            "Default Archer",
//            90,
//            15,
//            15,
//            R.drawable.archer_img,
//            "Bow"
//        )

        val classes = listOf(StickmanClass.WARRIOR, StickmanClass.MAGE, StickmanClass.ARCHER)
    }
}

class InvalidStickmanException(message: String): Exception(message)