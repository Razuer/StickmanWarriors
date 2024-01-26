package com.rejzerek.stickmanwarriors.feature_stickman.presentation.stickmen

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.OrderType
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.StickmanOrder

data class StickmenState(
    val stickmen: List<Stickman> = emptyList(),
    val stickmanOrder: StickmanOrder = StickmanOrder.Name(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false,
    var selectedStickman : Stickman? = null
)
