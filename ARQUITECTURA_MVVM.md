# Arquitectura MVVM - LeafDoctor

## 📁 Estructura del Proyecto

```
app/src/main/java/com/example/leafdoctor/
├── presentation/              # Capa de Presentación (UI + ViewModels)
│   ├── onboarding/
│   │   ├── OnboardingScreen.kt      # UI de la pantalla
│   │   └── OnboardingViewModel.kt   # Lógica de presentación
│   └── cropselection/
│       ├── CropSelectionScreen.kt   # UI de selección de cultivo
│       └── CropSelectionViewModel.kt # Lógica de presentación
│
├── domain/                    # Capa de Dominio
│   └── model/
│       └── Crop.kt           # Modelo de datos de cultivo
│
├── navigation/                # Sistema de navegación
│   └── NavGraph.kt           # Grafo de navegación
│
├── ui/                        # Temas y estilos
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
└── MainActivity.kt            # Actividad principal
```

## 🏗️ Componentes Implementados

### 1. **Presentation Layer** (Vista + ViewModel)

#### OnboardingScreen.kt
- **Responsabilidad**: Renderizar la UI de la pantalla de bienvenida
- **Características**:
  - Logo de LeafDoctor con fondo verde circular
  - Título y descripción de la app
  - 3 pasos explicativos con numeración
  - Botón "Comenzar análisis" que navega a la siguiente pantalla
  - Diseño totalmente responsive y fiel al mockup

#### OnboardingViewModel.kt
- **Responsabilidad**: Manejar la lógica de negocio de la pantalla de onboarding
- **Estado**: `OnboardingUiState` para manejar estados de carga
- **Principio**: Separación de lógica y UI siguiendo MVVM

#### CropSelectionScreen.kt ✨ NUEVO
- **Responsabilidad**: Pantalla de selección de tipo de cultivo
- **Características**:
  - Header con logo de LeafDoctor
  - Título y descripción
  - Grid 2x3 con cultivos disponibles (Naranja, Manzana, Papa, Tomate, Cereza)
  - Opción "Más" para expandir lista
  - Cards con emojis y nombres de cultivos
  - Navegación a pantalla de cámara al seleccionar cultivo

#### CropSelectionViewModel.kt ✨ NUEVO
- **Responsabilidad**: Manejar la lógica de selección de cultivos
- **Estado**: `CropSelectionUiState` con lista de cultivos y cultivo seleccionado
- **Funcionalidad**: Cargar lista de cultivos disponibles y manejar selección

### 2. **Domain Layer** ✨ NUEVO

#### Crop.kt
- **Responsabilidad**: Modelo de datos para representar un cultivo
- **Propiedades**:
  - `id`: Identificador único del cultivo
  - `nameResId`: Recurso de string para el nombre
  - `emoji`: Emoji representativo del cultivo
  - `isMore`: Flag para identificar la opción "Más"

### 3. **Navigation Layer**

#### NavGraph.kt
- **Responsabilidad**: Definir las rutas de navegación de la app
- **Rutas actuales**:
  - `Onboarding`: Pantalla de bienvenida
  - `CropSelection`: Selección de tipo de cultivo ✨
  - `Camera`: Captura de foto (próxima implementación)
- **Patrón**: Sealed class para type-safe navigation
- **Flujo**: Onboarding → CropSelection → Camera

### 4. **UI Theme**

#### Color.kt
- Colores personalizados de LeafDoctor:
  - `LeafGreen`: #34C759 (botones y elementos principales)
  - `LightGreen`: #B8E6C5 (círculos de pasos)
  - `DarkGreen`: #2FA84F (números en círculos)
  - `BackgroundLight`: #F5F5F5 (fondo)
  - `TextPrimary` y `TextSecondary`: Para textos

### 5. **Resources**

#### strings.xml
- Todos los textos externalizados para facilitar:
  - Traducción a otros idiomas
  - Mantenimiento centralizado
  - Buenas prácticas de Android

## 🎨 Diseños Implementados

### Pantalla de Onboarding
1. **Barra azul superior** (accent color de iOS style)
2. **Logo circular verde** con icono de hoja
3. **Título "LeafDoctor"** en negrita
4. **Descripción** sobre detección de enfermedades con IA
5. **3 pasos numerados**:
   - Círculos verdes claros con números
   - Texto descriptivo de cada paso
6. **Botón verde** "Comenzar análisis" con bordes redondeados

### Pantalla de Selección de Cultivo ✨ NUEVO
1. **Header blanco** con logo de hoja y texto "LeafDoctor"
2. **Título** "Selecciona tu cultivo" en negrita
3. **Descripción** "Elige el tipo de planta que deseas analizar"
4. **Grid 2x3** con cards de cultivos:
   - Naranja 🍊
   - Manzana 🍎
   - Papa 🥔
   - Tomate 🍅
   - Cereza 🍒
   - Más ⋯
5. **Cards** con fondo blanco, bordes redondeados, emoji grande y nombre

## 📦 Dependencias Añadidas

```kotlin
// Navigation Compose
implementation(libs.androidx.navigation.compose)

// ViewModel Compose
implementation(libs.androidx.lifecycle.viewmodel.compose)
```

## 🚀 Próximos Pasos

### Pantallas Completadas ✅
1. ✅ Onboarding Screen
2. ✅ Crop Selection Screen

### Próximas Implementaciones

1. **Pantalla de Cámara**:
   - Integración con CameraX
   - Captura de foto
   - Galería de imágenes
   - Preview de imagen capturada

2. **Data Layer** (para CNN y backend):
   ```
   data/
   ├── remote/         # API calls
   ├── local/          # Base de datos local
   ├── model/          # DTOs
   └── repository/     # Implementaciones
   ```

3. **Machine Learning**:
   - Integración de TensorFlow Lite
   - Modelo CNN para detección HLB
   - Procesamiento de imágenes
   - Inferencia en tiempo real

4. **Pantallas adicionales**:
   - Análisis/Loading screen
   - Resultados de detección
   - Detalles de enfermedad
   - Historial de análisis

## 💡 Ventajas de MVVM en este Proyecto

- ✅ Separación clara de responsabilidades
- ✅ Fácil testing de ViewModels
- ✅ UI reactiva con StateFlow
- ✅ Navegación desacoplada
- ✅ Escalabilidad para añadir modelo CNN
- ✅ Manejo de estados limpio

