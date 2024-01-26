package com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case

import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.repository.StickmanRepository
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.OrderType
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.StickmanOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStickmen(
    private val repository: StickmanRepository
) {
    operator fun invoke(
        stickmanOrder: StickmanOrder = StickmanOrder.Name(OrderType.Ascending)
    ): Flow<List<Stickman>> {
        return repository.getStickmen().map { stickmen ->
            when(stickmanOrder.orderType) {
                is OrderType.Ascending -> {
                    when (stickmanOrder) {
                        is StickmanOrder.Name -> stickmen.sortedBy { it.name.lowercase() }
                        is StickmanOrder.Class -> stickmen.sortedBy { it.`class`}
                        else -> stickmen.sortedBy { it.`class`}
                    }

                }
                is OrderType.Descending -> {
                    when (stickmanOrder) {
                        is StickmanOrder.Name -> stickmen.sortedByDescending { it.name.lowercase() }
                        is StickmanOrder.Class -> stickmen.sortedByDescending { it.`class`}
                        else -> stickmen.sortedByDescending { it.`class`}
                    }
                }

                else -> stickmen.sortedBy { it.name.lowercase() }
            }
        }
    }
}