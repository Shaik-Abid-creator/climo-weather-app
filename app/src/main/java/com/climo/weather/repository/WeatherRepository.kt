package com.climo.weather.repository

import com.climo.weather.model.WeatherResponse
import com.climo.weather.network.RetrofitClient
import com.climo.weather.util.Result

class WeatherRepository {
    private val weatherService = RetrofitClient.weatherService

    suspend fun getWeather(latitude: Double, longitude: Double): Result<WeatherResponse> {
        return try {
            val response = weatherService.getWeather(latitude, longitude)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error occurred")
        }
    }
}
