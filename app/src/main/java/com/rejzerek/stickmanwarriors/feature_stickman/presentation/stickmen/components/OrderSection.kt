package com.rejzerek.stickmanwarriors.feature_stickman.presentation.stickmen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.OrderType
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.StickmanOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    stickmanOrder: StickmanOrder = StickmanOrder.Name(OrderType.Ascending),
    onOrderChange: (StickmanOrder) -> Unit
) {
    Column(
        modifier = modifier
            .background(Color.Transparent)
            .clip(RoundedCornerShape(10.dp))
//            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly

        ) {
            DefaultRadioButton(
                text = "Name      ",
                selected = stickmanOrder is StickmanOrder.Name,
                onSelect = { onOrderChange(StickmanOrder.Name(stickmanOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Class       ",
                selected = stickmanOrder is StickmanOrder.Class,
                onSelect = { onOrderChange(StickmanOrder.Class(stickmanOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = stickmanOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(stickmanOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = stickmanOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(stickmanOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}