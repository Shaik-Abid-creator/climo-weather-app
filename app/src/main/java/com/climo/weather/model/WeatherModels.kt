package com.climo.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val elevation: Double,
    val current: CurrentWeather,
    val daily: DailyForecast
)

@Serializable
data class CurrentWeather(
    val time: String,
    val interval: Int,
    val temperature: Double,
    @SerialName("relative_humidity")
    val relativeHumidity: Int,
    @SerialName("apparent_temperature")
    val apparentTemperature: Double,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("precipitation")
    val precipitation: Double = 0.0,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("wind_speed_10m")
    val windSpeed: Double,
    @SerialName("wind_direction_10m")
    val windDirection: Int
)

@Serializable
data class DailyForecast(
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCode: List<Int>,
    @SerialName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerialName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerialName("precipitation_sum")
    val precipitationSum: List<Double>,
    @SerialName("precipitation_probability_max")
    val precipitationProbabilityMax: List<Int>
)

data class DailyWeather(
    val date: String,
    val weatherCode: Int,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val precipitationSum: Double,
    val precipitationProbability: Int
)

fun DailyForecast.toDailyWeatherList(): List<DailyWeather> {
    val safeSize = minOf(
        time.size,
        weatherCode.size,
        temperatureMax.size,
        temperatureMin.size,
        precipitationSum.size,
        precipitationProbabilityMax.size
    )

    return (0 until safeSize).map { index ->
        DailyWeather(
            date = time[index],
            weatherCode = weatherCode[index],
            temperatureMax = temperatureMax[index],
            temperatureMin = temperatureMin[index],
            precipitationSum = precipitationSum[index],
            precipitationProbability = precipitationProbabilityMax[index]
        )
    }
}

fun Int.getWeatherDescription(): String = when (this) {
    0 -> "Clear sky"
    1, 2 -> "Partly cloudy"
    3 -> "Overcast"
    45, 48 -> "Foggy"
    51, 53, 55 -> "Light drizzle"
    61, 63, 65 -> "Rain"
    71, 73, 75 -> "Snow"
    77 -> "Snow grains"
    80, 81, 82 -> "Rain showers"
    85, 86 -> "Snow showers"
    95, 96, 99 -> "Thunderstorm"
    else -> "Unknown"
}

fun Int.getWeatherIcon(): String = when (this) {
    0 -> "☀️"
    1, 2 -> "⛅"
    3 -> "☁️"
    45, 48 -> "🌫️"
    51, 53, 55 -> "🌦️"
    61, 63, 65 -> "🌧️"
    71, 73, 75 -> "❄️"
    77 -> "❄️"
    80, 81, 82 -> "🌧️"
    85, 86 -> "🌨️"
    95, 96, 99 -> "⛈️"
    else -> "🌡️"
}
