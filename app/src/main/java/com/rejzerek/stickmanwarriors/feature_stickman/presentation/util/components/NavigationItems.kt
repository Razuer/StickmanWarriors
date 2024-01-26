package com.rejzerek.stickmanwarriors.feature_stickman.presentation.util.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.util.Screen

object NavigationItems {
    val drawerNavItems: List<NavigationItem> = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Screen.HomeScreen.route
        ),
        NavigationItem(
            title = "Gallery",
            selectedIcon = Icons.Default.Edit,
            unselectedIcon = Icons.Outlined.Edit,
            route = Screen.GalleryScreen.route
        ),
        NavigationItem(
            title = "Collection",
            selectedIcon = Icons.Default.Face,
            unselectedIcon = Icons.Outlined.Face,
            route = Screen.StickmenScreen.route
        )
    )
}