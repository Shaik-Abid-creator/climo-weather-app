# Climo Weather App - Quick Start Guide

## Prerequisites

- **Android Studio** (Giraffe or newer)
- **JDK 17** or higher
- **Android SDK** (API 34 recommended)

## 5-Minute Setup

### Step 1: Open the Project

```bash
cd <project-root>
```

Open with Android Studio:
- File → Open → Select the project root folder
- Wait for Gradle sync to complete

### Step 2: Build

```bash
./gradlew build
```

### Step 3: Run

**Option A: Using Android Studio**
- Click the "Run" button (Shift+F10)
- Select an emulator or connected device

**Option B: Using Command Line**
```bash
./gradlew installDebug
```

### Step 4: Test

1. App launches with New York weather
2. Scroll down to see 7-day forecast
3. Enter coordinates (e.g., latitude: 51.5074, longitude: -0.1278 for London)
4. Tap "Search" to update weather

## Project Structure at a Glance

| File | Purpose |
|------|---------|
| `MainActivity.kt` | App entry point |
| `WeatherScreen.kt` | All UI composables |
| `WeatherViewModel.kt` | Business logic & state |
| `WeatherRepository.kt` | Data access |
| `WeatherService.kt` | API interface |
| `WeatherModels.kt` | Data classes |
| `Theme.kt` | Material 3 colors |

## Key Code Locations

**Fetch Weather**:
```kotlin
// In WeatherViewModel.kt
fun fetchWeather(lat: Double, lon: Double) { ... }
```

**Display Weather**:
```kotlin
// In WeatherScreen.kt
@Composable
fun CurrentWeatherCard(current: CurrentWeather) { ... }
```

**API Call**:
```kotlin
// In WeatherService.kt
@GET("forecast")
suspend fun getWeather(...): WeatherResponse
```

## Gradle Commands

```bash
./gradlew build              # Build APK
./gradlew clean              # Clean build
./gradlew assembleDebug      # Debug APK
./gradlew assembleRelease    # Release APK
./gradlew installDebug       # Install on device
./gradlew lint               # Run lint checks
```

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Gradle sync fails | Run `./gradlew clean` then sync again |
| App crashes | Check internet permission in AndroidManifest.xml |
| Compose preview broken | Update Android Studio to latest version |
| API returns error | Verify coordinates are valid (lat: -90 to 90, lon: -180 to 180) |

## Next Steps

1. **Explore the code** - Start with `MainActivity.kt`
2. **Modify colors** - Edit `app/src/main/res/values/colors.xml`
3. **Add features** - Extend `WeatherViewModel` with new methods
4. **Deploy** - Build release APK and upload to Google Play

## API Reference

**Open-Meteo Forecast API** (Free, no auth required)

```
GET https://api.open-meteo.com/v1/forecast
  ?latitude=40.7128
  &longitude=-74.0060
  &current=temperature,relative_humidity,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m
  &daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_probability_max
  &timezone=auto
```

## Documentation

- **README.md** - Full architecture and feature guide
- **PROJECT_SUMMARY.md** - Detailed project overview
- **QUICKSTART.md** - This file

---

**Happy coding!** 🚀
