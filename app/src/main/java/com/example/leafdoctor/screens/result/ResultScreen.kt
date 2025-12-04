package com.example.leafdoctor.screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafdoctor.R
import com.example.leafdoctor.ui.theme.*

@Composable
fun ResultScreen(
    cropName: String,
    diseaseName: String,
    diseaseDescription: String,
    onAnalyzeAnother: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        // Header
        ResultHeader()
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card de Detección
            DetectionCard(
                cropName = cropName,
                diseaseName = diseaseName
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Card de Descripción
            DescriptionCard(
                description = diseaseDescription
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Botón "Analizar otra hoja"
            Button(
                onClick = onAnalyzeAnother,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
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
                    text = stringResource(id = R.string.result_button_analyze_another),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun DetectionCard(
    cropName: String,
    diseaseName: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = LeafGreen,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Título con icono
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono de información
                Text(
                    text = "ⓘ",
                    fontSize = 24.sp,
                    color = LeafGreen
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = stringResource(id = R.string.result_detection_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Mensaje de detección
            Text(
                text = stringResource(
                    id = R.string.result_detection_message,
                    diseaseName,
                    cropName.lowercase()
                ),
                fontSize = 16.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DescriptionCard(
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = LeafGreen,
                shape = RoundedCornerShape(16.dp)
            ),
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
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Título con icono
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono de información
                Text(
                    text = "ⓘ",
                    fontSize = 24.sp,
                    color = LeafGreen
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = stringResource(id = R.string.result_description_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Descripción de la enfermedad
            Text(
                text = description,
                fontSize = 15.sp,
                color = TextPrimary,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun ResultHeader() {
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
fun ResultScreenPreview() {
    LeafDoctorTheme {
        ResultScreen(
            cropName = "Naranja",
            diseaseName = "huanglongbing",
            diseaseDescription = "El Huanglongbing (HLB), también conocido como enverdecimiento de los cítricos, es una grave enfermedad bacteriana que afecta a todas las plantas cítricas. Esta enfermedad, que no tiene cura, reduce drásticamente la calidad y la cantidad de la fruta, deteriora el árbol y finalmente lo mata.",
            onAnalyzeAnother = {}
        )
    }
}

