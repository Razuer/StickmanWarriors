package com.rejzerek.stickmanwarriors.feature_stickman.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.add_edit_stickman.AddEditStickmanScreen
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.details_stickman.DetailsStickmanScreen
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.home.GalleryScreen
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.home.HomeScreen
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.home.SharedViewModel
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.stickmen.StickmenScreen
import com.rejzerek.stickmanwarriors.feature_stickman.presentation.util.Screen
import com.rejzerek.stickmanwarriors.ui.theme.StickmanWarriorsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StickmanWarriorsTheme {
                val sharedViewModel : SharedViewModel = hiltViewModel()
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.StickmenScreen.route) {
                            StickmenScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditStickmanScreen.route +
                                    "?stickmanId={stickmanId}",
                            arguments = listOf(
                                navArgument(
                                    name = "stickmanId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            AddEditStickmanScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.DetailsStickmanScreen.route +
                                    "?stickmanId={stickmanId}",
                            arguments = listOf(
                                navArgument(
                                    name = "stickmanId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            DetailsStickmanScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.GalleryScreen.route
                        ){
                            GalleryScreen(
                                navController = navController, viewModel = sharedViewModel
                            )
                        }
                        composable(
                            route = Screen.HomeScreen.route
                        ){
                            HomeScreen(
                                navController = navController, viewModel = sharedViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}