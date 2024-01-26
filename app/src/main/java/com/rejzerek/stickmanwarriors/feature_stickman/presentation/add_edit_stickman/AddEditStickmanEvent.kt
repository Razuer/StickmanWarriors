package com.rejzerek.stickmanwarriors.feature_stickman.presentation.add_edit_stickman

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.StickmanClass

sealed class AddEditStickmanEvent {

    data class EnteredName(val value: String): AddEditStickmanEvent()
    data class EnteredClass(val value: StickmanClass, val image : Int): AddEditStickmanEvent()
    data class EnteredHealth(val value: Int): AddEditStickmanEvent()
    data class EnteredDefense(val value: Int): AddEditStickmanEvent()
    data class EnteredAttackDamage(val value: Int): AddEditStickmanEvent()
    data class EnteredWeapon(val value: String): AddEditStickmanEvent()

    data object SaveStickman: AddEditStickmanEvent()
}
