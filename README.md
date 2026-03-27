# Climo Weather App

A modern, production-ready Android weather application built with **Jetpack Compose**, **Kotlin**, and **Material 3** design. Climo fetches real-time weather data from the **Open-Meteo API** (no API key required) and displays current conditions, a 7-day forecast, and allows users to search by geographic coordinates.

## Architecture

**MVVM (Model-View-ViewModel)** with clean separation of concerns:

- **Model Layer** (`model/`): Data classes (DTOs) for Open-Meteo API responses
- **Network Layer** (`network/`): Retrofit service interface and OkHttp client configuration
- **Repository Layer** (`repository/`): Data access abstraction with error handling
- **ViewModel Layer** (`viewmodel/`): Business logic using StateFlow for reactive state management
- **UI Layer** (`ui/`): Jetpack Compose screens with Material 3 components

## Tech Stack

| Component | Technology |
|-----------|-----------|
| **UI Framework** | Jetpack Compose |
| **Language** | Kotlin 1.9.22 |
| **Networking** | Retrofit 2.10.0 + OkHttp 4.12.0 |
| **Serialization** | Kotlinx Serialization 1.6.2 |
| **State Management** | StateFlow (Kotlin Coroutines) |
| **Dependency Injection** | Manual (can be extended with Hilt) |
| **Design System** | Material 3 |
| **Minimum SDK** | API 24 (Android 7.0) |
| **Target SDK** | API 34 (Android 14) |

## Project Structure

```
<project-root>/
├── app/
│   ├── src/main/
│   │   ├── java/com/climo/weather/
│   │   │   ├── MainActivity.kt                 # App entry point
│   │   │   ├── model/
│   │   │   │   └── WeatherModels.kt           # Data classes & extensions
│   │   │   ├── network/
│   │   │   │   ├── WeatherService.kt          # Retrofit interface
│   │   │   │   └── RetrofitClient.kt          # HTTP client setup
│   │   │   ├── repository/
│   │   │   │   └── WeatherRepository.kt       # Data access layer
│   │   │   ├── viewmodel/
│   │   │   │   └── WeatherViewModel.kt        # Business logic
│   │   │   ├── ui/
│   │   │   │   ├── WeatherScreen.kt           # Main composables
│   │   │   │   └── theme/
│   │   │   │       ├── Theme.kt               # Material 3 colors
│   │   │   │       └── Type.kt                # Typography
│   │   │   └── util/
│   │   │       ├── Result.kt                  # Result wrapper
│   │   │       └── WeatherState.kt            # UI state sealed class
│   │   ├── res/
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   ├── colors.xml
│   │   │   │   └── themes.xml
│   │   │   └── values-night/
│   │   │       └── themes.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts                       # App dependencies
│   └── proguard-rules.pro
├── build.gradle.kts                           # Project-level config
├── settings.gradle.kts                        # Module configuration
└── README.md
```

## Features

### Current Implementation

- ✅ **Current Weather Dashboard**: Real-time temperature, humidity, wind speed, and weather conditions
- ✅ **7-Day Forecast**: Scrollable forecast cards with high/low temperatures and precipitation probability
- ✅ **Coordinate Search**: Input latitude/longitude to fetch weather for any location
- ✅ **Material 3 Dark Theme**: Modern, rounded UI with system-aware theming
- ✅ **Error Handling**: Graceful error states with retry functionality
- ✅ **Loading States**: Smooth loading indicators during API calls
- ✅ **Responsive Layout**: Optimized for various screen sizes

### Weather Data Provided

- Current temperature and "feels like" temperature
- Weather code with human-readable descriptions and emoji icons
- Humidity and wind speed/direction
- Precipitation amount
- 7-day forecast with max/min temperatures and rain probability

## Getting Started

### Prerequisites

- **Android Studio** (Giraffe or newer)
- **JDK 17** or higher
- **Android SDK** (API 34 recommended)
- **Gradle 8.2+**

### Building the Project

1. **Clone or extract the project**:
   ```bash
   cd <project-root>
   ```

2. **Open in Android Studio**:
   - File → Open → Select the project root folder
   - Android Studio will automatically sync Gradle dependencies

3. **Build the APK**:
   ```bash
   ./gradlew assembleDebug    # Debug APK
   ./gradlew assembleRelease  # Release APK (requires signing config)
   ```

4. **Run on Emulator or Device**:
   ```bash
   ./gradlew installDebug
   ```

   Or use Android Studio's "Run" button (Shift+F10).

### Gradle Build Commands

```bash
./gradlew build              # Full build
./gradlew clean              # Clean build artifacts
./gradlew lint               # Run lint checks
./gradlew test               # Run unit tests
./gradlew connectedAndroidTest  # Run instrumented tests
```

### Release Readiness

Before shipping, follow `DEPLOYMENT_CHECKLIST.md` for environment setup, quality gates, and rollout checks.

For release signing, copy `keystore.properties.example` to `keystore.properties` and provide your real keystore values (the real file is gitignored).

For local release verification, run:

```bash
./scripts/release_build.sh
```

For CI release artifacts, use the manual workflow: `.github/workflows/android-release.yml`.
Secret setup details are documented in `docs/deployment/RELEASE_SECRETS.md`.

## API Integration

### Open-Meteo Forecast API

**Base URL**: `https://api.open-meteo.com/v1/`

**Endpoint**: `GET /forecast`

**Parameters**:
- `latitude` (required): Geographic latitude
- `longitude` (required): Geographic longitude
- `current`: Current weather parameters
- `daily`: Daily forecast parameters
- `timezone`: Timezone (default: auto)

**Example Request**:
```
https://api.open-meteo.com/v1/forecast?latitude=40.7128&longitude=-74.0060&current=temperature,relative_humidity,weather_code,wind_speed_10m&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max
```

**No API key required** — Open-Meteo is a free, open-source weather API.

## State Management

### WeatherState (Sealed Class)

```kotlin
sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
```

### ViewModel with StateFlow

```kotlin
val weatherState: StateFlow<WeatherState> = _weatherState
val latitude: StateFlow<Double> = _latitude
val longitude: StateFlow<Double> = _longitude

fun fetchWeather(lat: Double, lon: Double) { ... }
fun updateCoordinates(lat: Double, lon: Double) { ... }
```

## Composable Components

### Main Screens

| Composable | Purpose |
|-----------|---------|
| `WeatherScreen()` | Root composable with state routing |
| `LoadingScreen()` | Loading indicator |
| `SuccessScreen()` | Main weather dashboard |
| `ErrorScreen()` | Error state with retry button |

### UI Components

| Composable | Purpose |
|-----------|---------|
| `CurrentWeatherCard()` | Large temperature and condition display |
| `WeatherDetailsGrid()` | Humidity, wind, precipitation grid |
| `DetailCard()` | Reusable metric card |
| `ForecastCard()` | Individual day forecast |
| `SearchCoordinatesSection()` | Latitude/longitude input form |

## Material 3 Theme

### Color Palette

**Dark Mode** (Primary):
- Primary: `#00D4FF` (Cyan)
- Secondary: `#FFBB6A` (Orange)
- Tertiary: `#7CDFBF` (Green)
- Background: `#0F1419` (Deep charcoal)
- Surface: `#1A1F26` (Card background)

**Light Mode**:
- Primary: `#0284C7` (Sky blue)
- Secondary: `#EA580C` (Orange)
- Tertiary: `#10B981` (Green)
- Background: `#F8FAFC` (Off-white)
- Surface: `#FFFFFF` (White)

### Typography

Material 3 standard typography scales with system-aware font sizing.

## Error Handling

### Result Wrapper

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
```

### Network Error Scenarios

- **No Internet**: Displays error message with retry button
- **Invalid Coordinates**: API returns empty or error response
- **Timeout**: 30-second timeout configured in OkHttp
- **Serialization Error**: Graceful fallback to error state

## Extending the App

### Adding Hilt Dependency Injection

Replace manual repository instantiation in ViewModel:

```kotlin
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() { ... }
```

### Adding Location Services

Integrate `android.location.LocationManager` to auto-fetch user location:

```kotlin
val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
viewModel.fetchWeather(location.latitude, location.longitude)
```

### Adding Local Caching

Integrate Room database to cache weather responses:

```kotlin
@Entity
data class CachedWeather(
    @PrimaryKey val id: Int,
    val latitude: Double,
    val longitude: Double,
    val response: String, // JSON
    val timestamp: Long
)
```

## Testing

### Unit Tests

```kotlin
@Test
fun testWeatherViewModelFetchWeather() {
    val viewModel = WeatherViewModel()
    viewModel.fetchWeather(40.7128, -74.0060)
    
    val state = viewModel.weatherState.value
    assertTrue(state is WeatherState.Success || state is WeatherState.Error)
}
```

### Instrumented Tests

```kotlin
@RunWith(AndroidJUnit4::class)
class WeatherScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCurrentWeatherCardDisplaysTemperature() {
        composeTestRule.setContent {
            CurrentWeatherCard(mockCurrentWeather)
        }
        composeTestRule.onNodeWithText("25°C").assertIsDisplayed()
    }
}
```

## Performance Optimization

- **Memoization**: Composables use `remember` to prevent unnecessary recompositions
- **LazyColumn**: 7-day forecast uses lazy loading for efficiency
- **Coroutines**: Network calls run on `Dispatchers.IO` via `viewModelScope`
- **Serialization**: Kotlinx Serialization is faster than GSON/Moshi
- **Proguard**: Release builds are minified and obfuscated

## Troubleshooting

### Build Fails: "Unresolved reference: Retrofit"

Ensure Gradle sync completed successfully:
```bash
./gradlew clean
./gradlew build
```

### App Crashes: "Unable to resolve host"

Check internet permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### Compose Preview Not Rendering

Ensure Android Studio is updated to the latest version and Compose compiler is compatible with your Kotlin version.

## License

This project is provided as-is for educational and commercial use.

## Support

For issues, feature requests, or contributions, please refer to the project documentation or contact the development team.

---

**Built with ❤️ using Jetpack Compose and Kotlin**
