package com.rejzerek.stickmanwarriors.feature_stickman.presentation.stickmen

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.StickmanOrder

sealed class StickmenEvent {
    data class Order(val stickmanOrder: StickmanOrder): StickmenEvent()
    data class DeleteStickman(val stickman: Stickman): StickmenEvent()
    data object RestoreStickman: StickmenEvent()
    data object ToggleOrderSection: StickmenEvent()
}