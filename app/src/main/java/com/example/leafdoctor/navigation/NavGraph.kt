package com.example.leafdoctor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leafdoctor.presentation.cropselection.CropSelectionScreen
import com.example.leafdoctor.presentation.onboarding.OnboardingScreen

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object CropSelection : Screen("crop_selection")
    object Camera : Screen("camera")
    // Añadiremos más pantallas según avancemos
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onStartAnalysis = {
                    navController.navigate(Screen.CropSelection.route)
                }
            )
        }
        
        composable(Screen.CropSelection.route) {
            CropSelectionScreen(
                onCropSelected = { crop ->
                    // Navegar a la pantalla de cámara con el cultivo seleccionado
                    navController.navigate(Screen.Camera.route)
                }
            )
        }
        
        composable(Screen.Camera.route) {
            // Aquí irá la pantalla de cámara
            // Por ahora, un placeholder
        }
    }
}

