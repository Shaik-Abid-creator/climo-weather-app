package com.climo.weather.network

import com.climo.weather.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature,relative_humidity,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m",
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_probability_max",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}
