package com.climo.weather.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WeatherModelsTest {

    @Test
    fun `getWeatherDescription returns known and fallback values`() {
        assertEquals("Clear sky", 0.getWeatherDescription())
        assertEquals("Rain showers", 80.getWeatherDescription())
        assertEquals("Unknown", 999.getWeatherDescription())
    }

    @Test
    fun `getWeatherIcon returns known and fallback icons`() {
        assertEquals("☀️", 0.getWeatherIcon())
        assertEquals("⛈️", 95.getWeatherIcon())
        assertEquals("🌡️", 999.getWeatherIcon())
    }

    @Test
    fun `toDailyWeatherList maps all rows when arrays are aligned`() {
        val forecast = DailyForecast(
            time = listOf("2026-03-27", "2026-03-28"),
            weatherCode = listOf(0, 1),
            temperatureMax = listOf(20.0, 22.0),
            temperatureMin = listOf(10.0, 12.0),
            precipitationSum = listOf(0.0, 1.2),
            precipitationProbabilityMax = listOf(10, 60)
        )

        val result = forecast.toDailyWeatherList()

        assertEquals(2, result.size)
        assertEquals("2026-03-27", result[0].date)
        assertEquals(22.0, result[1].temperatureMax, 0.0)
    }

    @Test
    fun `toDailyWeatherList truncates safely when arrays are uneven`() {
        val forecast = DailyForecast(
            time = listOf("2026-03-27", "2026-03-28", "2026-03-29"),
            weatherCode = listOf(0, 1),
            temperatureMax = listOf(20.0, 22.0, 24.0),
            temperatureMin = listOf(10.0, 12.0, 14.0),
            precipitationSum = listOf(0.0, 1.2, 2.4),
            precipitationProbabilityMax = listOf(10, 60, 90)
        )

        val result = forecast.toDailyWeatherList()

        assertEquals(2, result.size)
        assertTrue(result.all { it.date != "2026-03-29" })
    }
}
