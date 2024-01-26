package com.rejzerek.stickmanwarriors.feature_stickman.presentation.stickmen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rejzerek.stickmanwarriors.R
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.StickmanClass
import com.rejzerek.stickmanwarriors.ui.theme.archerColor
import com.rejzerek.stickmanwarriors.ui.theme.mageColor
import com.rejzerek.stickmanwarriors.ui.theme.warriorColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickmanItem(
    stickman: Stickman,
    modifier: Modifier = Modifier,
    onDeleteClick : () -> Unit,
    onClick : () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable (
                onLongClick = onDeleteClick,
                onClick = onClick,
            )
            .background(getStickmanBackgroundColor(stickman.`class`))
            .padding(16.dp)

    ) {
        Row {
            Image(
                painter = painterResource(id = stickman.imageResource),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.Black, CircleShape)
                    .size(70.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stickman.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(end = 15.dp)

                )
                Divider(color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(2.dp, 5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(painterResource(id = R.drawable.outline_ecg_heart_24), contentDescription = null)
                    Text(text = "${stickman.health}", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Icon(painterResource(id = R.drawable.outline_shield_24), contentDescription = null)
                    Text(text = "${stickman.attackDamage}", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Icon(painterResource(id = R.drawable.outline_swords_24), contentDescription = null)
                    Text(text = "${stickman.defense}", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Composable
fun getStickmanBackgroundColor(stickmanClass: StickmanClass): Color {
    return when (stickmanClass) {
        StickmanClass.WARRIOR -> warriorColor
        StickmanClass.MAGE -> mageColor
        StickmanClass.ARCHER -> archerColor
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmation(onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.error),
        onClick = onDeleteClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            Spacer(modifier = Modifier.width(4.dp))
            BasicText("Delete", style = MaterialTheme.typography.titleMedium)
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun DecoratedComposablePreview() {
//    StickmanItem(Stickman.DEFAULT_ARCHER, onDeleteClick = {}, onClick = {} )
////    StickmanItem(Stickman.DEFAULT_WARRIOR)
////    StickmanItem(Stickman.DEFAULT_MAGE)
//}