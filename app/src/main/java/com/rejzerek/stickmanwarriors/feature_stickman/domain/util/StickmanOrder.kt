package com.rejzerek.stickmanwarriors.feature_stickman.domain.util

sealed class StickmanOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : StickmanOrder(orderType)
    class Class(orderType: OrderType) : StickmanOrder(orderType)

    fun copy(orderType: OrderType) : StickmanOrder {
        return when(this) {
            is Name -> Name(orderType)
            is Class -> Class(orderType)
            else -> Name(orderType)
        }
    }
}