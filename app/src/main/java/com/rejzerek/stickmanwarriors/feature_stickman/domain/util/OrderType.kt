package com.rejzerek.stickmanwarriors.feature_stickman.domain.util

sealed class OrderType {
    data object Ascending:OrderType()
    data object Descending:OrderType()
}