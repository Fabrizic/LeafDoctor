package com.example.leafdoctor.screens.imagecapture

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafdoctor.R
import com.example.leafdoctor.ui.theme.*

@Composable
fun ImageCaptureScreen(
    cropName: String,
    onTakePhoto: () -> Unit = {},
    onImageSelected: (Uri) -> Unit = {}
) {
    // Launcher para seleccionar imagen de cualquier ubicación (galería, descargas, etc.)
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onImageSelected(it) }
    }

    ImageCaptureContent(
        cropName = cropName,
        onTakePhoto = onTakePhoto,
        onUploadImage = {
            // "image/*" permite seleccionar cualquier tipo de imagen desde cualquier ubicación
            imagePickerLauncher.launch("image/*")
        }
    )
}

@Composable
fun ImageCaptureContent(
    cropName: String,
    onTakePhoto: () -> Unit = {},
    onUploadImage: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        // Header
        ImageCaptureHeader()
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título con nombre del cultivo
            Text(
                text = cropName,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Descripción
            Text(
                text = stringResource(id = R.string.image_capture_description),
                fontSize = 16.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Card principal con botones
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Icono de cámara
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .background(
                                color = LightGreen.copy(alpha = 0.3f),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.photo_camera_24px),
                            contentDescription = "Camera",
                            modifier = Modifier.size(70.dp),
                            colorFilter = ColorFilter.tint(LeafGreen)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Título de la sección
                    Text(
                        text = stringResource(id = R.string.image_capture_title),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Instrucción
                    Text(
                        text = stringResource(id = R.string.image_capture_instruction),
                        fontSize = 14.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Botón "Tomar foto"
                    Button(
                        onClick = onTakePhoto,
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
                            text = stringResource(id = R.string.image_capture_button_take_photo),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    // Botón "Subir imagen"
                    OutlinedButton(
                        onClick = onUploadImage,
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
                            text = stringResource(id = R.string.image_capture_button_upload_image),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCaptureHeader() {
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
fun ImageCaptureScreenPreview() {
    LeafDoctorTheme {
        ImageCaptureContent(
            cropName = "Naranja",
            onTakePhoto = {},
            onUploadImage = {}
        )
    }
}
