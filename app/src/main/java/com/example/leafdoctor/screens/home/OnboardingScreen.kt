package com.example.leafdoctor.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.leafdoctor.R
import com.example.leafdoctor.ui.theme.*

@Composable
fun OnboardingScreen(
    onStartAnalysis: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        // Barra azul superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color(0xFF007AFF))
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(LeafGreen),
                contentAlignment = Alignment.Center
            ) {
                LeafIcon()
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Título
            Text(
                text = stringResource(id = R.string.onboarding_title),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Descripción
            Text(
                text = stringResource(id = R.string.onboarding_description),
                fontSize = 16.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Pasos
            OnboardingStep(number = "1", text = stringResource(id = R.string.onboarding_step1))
            Spacer(modifier = Modifier.height(16.dp))
            OnboardingStep(number = "2", text = stringResource(id = R.string.onboarding_step2))
            Spacer(modifier = Modifier.height(16.dp))
            OnboardingStep(number = "3", text = stringResource(id = R.string.onboarding_step3))
            
            Spacer(modifier = Modifier.height(56.dp))
            
            // Botón
            Button(
                onClick = onStartAnalysis,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LeafGreen
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.onboarding_button),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun OnboardingStep(
    number: String,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Círculo con número
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(LightGreen),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkGreen
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        // Texto del paso
        Text(
            text = text,
            fontSize = 16.sp,
            color = TextSecondary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LeafIcon() {
    // Icono simple de hoja usando Canvas o un ícono vectorial
    Text(
        text = "🌿",
        fontSize = 80.sp
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    LeafDoctorTheme {
        OnboardingScreen()
    }
}

