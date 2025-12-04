package com.example.leafdoctor.navigation

import android.net.Uri
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.leafdoctor.screens.cropselection.CropSelectionScreen
import com.example.leafdoctor.screens.home.OnboardingScreen
import com.example.leafdoctor.screens.imagecapture.ImageCaptureScreen
import com.example.leafdoctor.screens.imagepreview.ImagePreviewScreen
import com.example.leafdoctor.screens.result.ResultScreen

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object CropSelection : Screen("crop_selection")
    object ImageCapture : Screen("image_capture/{cropName}") {
        fun createRoute(cropName: String) = "image_capture/$cropName"
    }
    object ImagePreview : Screen("image_preview/{cropName}") {
        fun createRoute(cropName: String) = "image_preview/$cropName"
    }
    object Result : Screen("result/{cropName}") {
        fun createRoute(cropName: String) = "result/$cropName"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Onboarding.route
) {
    // Estado compartido para la URI de la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    
    // Mapeo de IDs a nombres para mostrar
    val cropNameMap = mapOf(
        "orange" to "Naranja",
        "apple" to "Manzana",
        "potato" to "Papa",
        "tomato" to "Tomate",
        "cherry" to "Cereza"
    )
    
    // Datos del prototipo (enfermedad simulada)
    val prototypeDiseaseName = "huanglongbing"
    val prototypeDiseaseDescription = "El Huanglongbing (HLB), también conocido como enverdecimiento de los cítricos, es una grave enfermedad bacteriana que afecta a todas las plantas cítricas. Esta enfermedad, que no tiene cura, reduce drásticamente la calidad y la cantidad de la fruta, deteriora el árbol y finalmente lo mata."
    
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
                    // Navegar a la pantalla de captura de imagen con el cultivo seleccionado
                    val route = Screen.ImageCapture.createRoute(crop.id)
                    navController.navigate(route)
                }
            )
        }
        
        composable(
            route = Screen.ImageCapture.route,
            arguments = listOf(
                navArgument("cropName") { 
                    type = NavType.StringType 
                }
            )
        ) { backStackEntry ->
            val cropId = backStackEntry.arguments?.getString("cropName") ?: ""
            val cropDisplayName = cropNameMap[cropId] ?: cropId
            
            ImageCaptureScreen(
                cropName = cropDisplayName,
                onTakePhoto = {
                    // Aquí implementaremos la funcionalidad de la cámara
                },
                onImageSelected = { uri ->
                    selectedImageUri = uri
                    navController.navigate(Screen.ImagePreview.createRoute(cropId))
                }
            )
        }
        
        composable(
            route = Screen.ImagePreview.route,
            arguments = listOf(
                navArgument("cropName") { 
                    type = NavType.StringType 
                }
            )
        ) { backStackEntry ->
            val cropId = backStackEntry.arguments?.getString("cropName") ?: ""
            val cropDisplayName = cropNameMap[cropId] ?: cropId
            
            ImagePreviewScreen(
                cropName = cropDisplayName,
                imageUri = selectedImageUri,
                onAnalyze = {
                    // Navegar a la pantalla de resultados
                    navController.navigate(Screen.Result.createRoute(cropId))
                },
                onRetake = {
                    // Volver a la pantalla de captura
                    navController.popBackStack()
                }
            )
        }
        
        composable(
            route = Screen.Result.route,
            arguments = listOf(
                navArgument("cropName") { 
                    type = NavType.StringType 
                }
            )
        ) { backStackEntry ->
            val cropId = backStackEntry.arguments?.getString("cropName") ?: ""
            val cropDisplayName = cropNameMap[cropId] ?: cropId
            
            ResultScreen(
                cropName = cropDisplayName,
                diseaseName = prototypeDiseaseName,
                diseaseDescription = prototypeDiseaseDescription,
                onAnalyzeAnother = {
                    // Volver a la pantalla de selección de cultivo
                    navController.navigate(Screen.CropSelection.route) {
                        // Limpiar el back stack hasta el inicio
                        popUpTo(Screen.Onboarding.route) { inclusive = false }
                    }
                }
            )
        }
    }
}
