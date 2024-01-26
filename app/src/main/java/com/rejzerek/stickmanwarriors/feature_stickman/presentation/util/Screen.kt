package com.rejzerek.stickmanwarriors.feature_stickman.presentation.util

sealed class Screen(val route: String) {
    object StickmenScreen: Screen("stickmen_screen")
    object AddEditStickmanScreen: Screen("add_edit_stickman_screen")
    object HomeScreen: Screen("home_screen")
    object GalleryScreen: Screen("gallery_screen")
    object DetailsStickmanScreen: Screen("details_stickman_screen")
}