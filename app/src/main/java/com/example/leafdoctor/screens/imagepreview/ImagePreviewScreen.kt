package com.example.leafdoctor.screens.imagepreview

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.leafdoctor.R
import com.example.leafdoctor.ui.theme.*

@Composable
fun ImagePreviewScreen(
    cropName: String,
    imageUri: Uri?,
    onAnalyze: () -> Unit = {},
    onRetake: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        // Header
        ImagePreviewHeader()
        
        // Content con scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título con nombre del cultivo
            Text(
                text = cropName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Descripción
            Text(
                text = stringResource(id = R.string.image_preview_description),
                fontSize = 16.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Card con la imagen - tamaño adaptable
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUri)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Imagen seleccionada",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillWidth,
                            loading = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        color = LeafGreen,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            },
                            error = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Error al cargar imagen",
                                        color = TextSecondary,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay imagen seleccionada",
                                color = TextSecondary,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Botones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Botón "Analizar imagen"
                Button(
                    onClick = onAnalyze,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LeafGreen
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.image_preview_button_analyze),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                
                // Botón "Seleccionar otra"
                OutlinedButton(
                    onClick = onRetake,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 1.5.dp
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.image_preview_button_retake),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePreviewHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
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

@Preview(showBackground = true)
@Composable
fun ImagePreviewScreenPreview() {
    LeafDoctorTheme {
        ImagePreviewScreen(
            cropName = "Naranja",
            imageUri = null,
            onAnalyze = {},
            onRetake = {}
        )
    }
}
