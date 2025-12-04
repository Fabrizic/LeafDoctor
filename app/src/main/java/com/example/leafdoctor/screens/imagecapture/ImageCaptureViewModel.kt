package com.example.leafdoctor.screens.imagecapture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ImageCaptureUiState(
    val cropName: String = "",
    val imageUri: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ImageCaptureViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ImageCaptureUiState())
    val uiState: StateFlow<ImageCaptureUiState> = _uiState.asStateFlow()

    fun setCropName(cropName: String) {
        _uiState.value = _uiState.value.copy(cropName = cropName)
    }

    fun onTakePhoto() {
        viewModelScope.launch {
            // Aquí implementaremos la lógica para abrir la cámara
            // Por ahora solo registramos la acción
        }
    }

    fun onUploadImage() {
        viewModelScope.launch {
            // Aquí implementaremos la lógica para seleccionar una imagen de la galería
            // Por ahora solo registramos la acción
        }
    }

    fun setImageUri(uri: String?) {
        _uiState.value = _uiState.value.copy(imageUri = uri)
    }

    fun setLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

    fun setError(error: String?) {
        _uiState.value = _uiState.value.copy(error = error)
    }
}

