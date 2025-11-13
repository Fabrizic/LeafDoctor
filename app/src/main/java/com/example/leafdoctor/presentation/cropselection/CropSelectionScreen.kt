package com.example.leafdoctor.presentation.cropselection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafdoctor.R
import com.example.leafdoctor.domain.model.Crop
import com.example.leafdoctor.ui.theme.*

@Composable
fun CropSelectionScreen(
    viewModel: CropSelectionViewModel = viewModel(),
    onCropSelected: (Crop) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    CropSelectionContent(
        crops = uiState.crops,
        onCropClick = { crop ->
            viewModel.onCropSelected(crop)
            if (!crop.isMore) {
                onCropSelected(crop)
            }
        }
    )
}

@Composable
fun CropSelectionContent(
    crops: List<Crop>,
    onCropClick: (Crop) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        // Header
        CropSelectionHeader()
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Título
            Text(
                text = stringResource(id = R.string.crop_selection_title),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Descripción
            Text(
                text = stringResource(id = R.string.crop_selection_description),
                fontSize = 16.sp,
                color = TextSecondary,
                lineHeight = 22.sp
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Grid de cultivos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(crops) { crop ->
                    CropCard(
                        crop = crop,
                        onClick = { onCropClick(crop) }
                    )
                }
            }
        }
    }
}

@Composable
fun CropSelectionHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono de hoja
        Text(
            text = "🌿",
            fontSize = 32.sp
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Texto LeafDoctor
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}

@Composable
fun CropCard(
    crop: Crop,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Emoji/Icono del cultivo
            Text(
                text = crop.emoji,
                fontSize = if (crop.isMore) 48.sp else 64.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Nombre del cultivo
            Text(
                text = stringResource(id = crop.nameResId),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CropSelectionScreenPreview() {
    LeafDoctorTheme {
        CropSelectionContent(
            crops = listOf(
                Crop("orange", R.string.crop_orange, "🍊"),
                Crop("apple", R.string.crop_apple, "🍎"),
                Crop("potato", R.string.crop_potato, "🥔"),
                Crop("tomato", R.string.crop_tomato, "🍅"),
                Crop("cherry", R.string.crop_cherry, "🍒"),
                Crop("more", R.string.crop_more, "⋯", isMore = true)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CropCardPreview() {
    LeafDoctorTheme {
        CropCard(
            crop = Crop("orange", R.string.crop_orange, "🍊"),
            onClick = {}
        )
    }
}

