package com.example.rustoremvp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.NavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.rustoremvp.PrefManager
import com.example.rustoremvp.screens.OnBoardingScreen
import com.example.rustoremvp.screens.CatalogScreen
import com.example.rustoremvp.screens.AppDetailsScreen
import com.example.rustoremvp.screens.FullScreenScreenshotScreen

@Composable
fun NavGraph(prefManager: PrefManager) {
    val navController = rememberNavController()
    val startDestination = if (prefManager.isFirstLaunch()) "onboarding" else "catalog"
    NavHost(navController = navController, startDestination = startDestination){
        composable("onboarding") {
            OnBoardingScreen (onNavigate = {
                prefManager.setOnboardingDone()
                navController.navigate("catalog"){
                    popUpTo("onboarding") {inclusive = true}
                }
            })
        }
        composable("catalog"){
            CatalogScreen(navController)
        }
        composable("screenshot/{appId}/{startIndex}",
            listOf(
                navArgument("appId") {type = NavType.IntType},
                navArgument("startIndex") {type = NavType.IntType})
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getInt("appId") ?: return@composable
            val startIndex = backStackEntry.arguments?.getInt("startIndex") ?: 0
            FullScreenScreenshotScreen(
                appId = appId,
                startIndex = startIndex,
                onBack = { navController.popBackStack()}
            )

        }

        composable(
            "details/{appId}",
            arguments = listOf(navArgument("appId") { type = NavType.IntType})
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getInt("appId")
            AppDetailsScreen(navController, appId)
        }
    }
}