package com.example.leafdoctor.screens.cropselection

import androidx.lifecycle.ViewModel
import com.example.leafdoctor.R
import com.example.leafdoctor.domain.model.Crop
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CropSelectionViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(CropSelectionUiState())
    val uiState: StateFlow<CropSelectionUiState> = _uiState.asStateFlow()
    
    init {
        loadCrops()
    }
    
    private fun loadCrops() {
        val crops = listOf(
            Crop(
                id = "orange",
                nameResId = R.string.crop_orange,
                emoji = "🍊"
            ),
            Crop(
                id = "apple",
                nameResId = R.string.crop_apple,
                emoji = "🍎"
            ),
            Crop(
                id = "potato",
                nameResId = R.string.crop_potato,
                emoji = "🥔"
            ),
            Crop(
                id = "tomato",
                nameResId = R.string.crop_tomato,
                emoji = "🍅"
            ),
            Crop(
                id = "cherry",
                nameResId = R.string.crop_cherry,
                emoji = "🍒"
            ),
            Crop(
                id = "more",
                nameResId = R.string.crop_more,
                emoji = "⋯",
                isMore = true
            )
        )
        
        _uiState.value = _uiState.value.copy(crops = crops)
    }
    
    fun onCropSelected(crop: Crop) {
        if (crop.isMore) {
            // Mostrar lista completa de cultivos
        } else {
            _uiState.value = _uiState.value.copy(selectedCrop = crop)
        }
    }
}

data class CropSelectionUiState(
    val crops: List<Crop> = emptyList(),
    val selectedCrop: Crop? = null,
    val isLoading: Boolean = false
)

