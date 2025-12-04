package com.example.leafdoctor.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()
    
    fun onStartAnalysisClick() {
        // Aquí manejaremos la navegación a la siguiente pantalla
    }
}

data class OnboardingUiState(
    val isLoading: Boolean = false
)

