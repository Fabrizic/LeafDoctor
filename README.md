# 🌿 LeafDoctor

Aplicación móvil Android para detectar enfermedades HLB (Huanglongbing) en hojas de cultivos usando Redes Neuronales Convolucionales (CNN).

## 📱 Descripción

LeafDoctor es una aplicación que utiliza inteligencia artificial para detectar enfermedades en cultivos de manera rápida y precisa. El usuario puede:

1. Seleccionar su tipo de cultivo
2. Tomar una foto de la hoja afectada
3. Obtener información sobre la enfermedad detectada

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** con las siguientes capas:

- **Presentation**: UI (Jetpack Compose) + ViewModels
- **Domain**: Lógica de negocio y casos de uso (a implementar)
- **Data**: Repositorios y fuentes de datos (a implementar)

Ver [ARQUITECTURA_MVVM.md](ARQUITECTURA_MVVM.md) para más detalles.

## 🛠️ Tecnologías

- **Lenguaje**: Kotlin
- **UI**: Jetpack Compose
- **Arquitectura**: MVVM
- **Navegación**: Navigation Compose
- **IA**: TensorFlow Lite (a implementar)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 15)

## 📦 Dependencias

```kotlin
// Jetpack Compose
implementation(libs.androidx.compose.bom)
implementation(libs.androidx.material3)

// Navigation
implementation(libs.androidx.navigation.compose)

// ViewModel
implementation(libs.androidx.lifecycle.viewmodel.compose)
```

## 🚀 Cómo Ejecutar

1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza Gradle
4. Ejecuta en un emulador o dispositivo físico

```bash
./gradlew build
```

## 📸 Pantallas Implementadas

### ✅ Onboarding Screen
- Pantalla de bienvenida con información de la app
- Logo de LeafDoctor
- 3 pasos explicativos
- Botón para comenzar el análisis

### ✅ Crop Selection Screen ✨ NUEVO
- Header con logo de LeafDoctor
- Selección de tipo de cultivo
- Grid con 6 opciones (Naranja, Manzana, Papa, Tomate, Cereza, Más)
- Cards interactivas con emojis
- Navegación a pantalla de cámara

### 🚧 En Desarrollo
- Captura de foto (Cámara/Galería)
- Análisis con CNN
- Resultados y recomendaciones

## 👨‍💻 Desarrollo

### Estructura del Código

```
app/src/main/java/com/example/leafdoctor/
├── presentation/       # Pantallas y ViewModels
│   ├── onboarding/    # Pantalla de bienvenida
│   └── cropselection/ # Selección de cultivo ✨
├── domain/            # Modelos de dominio ✨
│   └── model/        
├── navigation/        # Sistema de navegación
├── ui/theme/          # Tema y colores
└── MainActivity.kt    # Punto de entrada
```

### Colores del Tema

- **LeafGreen**: #34C759 (Botones principales)
- **LightGreen**: #B8E6C5 (Elementos secundarios)
- **DarkGreen**: #2FA84F (Acentos)
- **BackgroundLight**: #F5F5F5 (Fondo)

## 📋 Funcionalidades

### Completadas ✅
- [x] Pantalla de onboarding
- [x] Pantalla de selección de cultivo
- [x] Sistema de navegación
- [x] Arquitectura MVVM base

### Próximas ⏳
- [ ] Integración con cámara (CameraX)
- [ ] Modelo CNN para detección HLB
- [ ] Pantalla de análisis/loading
- [ ] Pantalla de resultados
- [ ] Base de datos local de enfermedades
- [ ] Historial de análisis
- [ ] Sistema de notificaciones
- [ ] Modo offline

## 🤝 Contribuir

Este proyecto está en desarrollo activo. Siguiendo MVVM para mantener el código limpio y escalable.

## 📄 Licencia

[Especificar licencia]

## 👤 Autor

[Tu nombre]

---

**LeafDoctor** - Detecta enfermedades en tus cultivos usando inteligencia artificial 🌱

