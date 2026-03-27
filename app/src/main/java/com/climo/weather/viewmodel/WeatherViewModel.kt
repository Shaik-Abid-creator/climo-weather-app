package com.climo.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climo.weather.repository.WeatherRepository
import com.climo.weather.util.Result
import com.climo.weather.util.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    private val _latitude = MutableStateFlow(40.7128)
    val latitude: StateFlow<Double> = _latitude

    private val _longitude = MutableStateFlow(-74.0060)
    val longitude: StateFlow<Double> = _longitude

    init {
        fetchWeather(40.7128, -74.0060)
    }

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            _latitude.value = lat
            _longitude.value = lon

            val result = repository.getWeather(lat, lon)
            _weatherState.value = when (result) {
                is Result.Success -> WeatherState.Success(result.data)
                is Result.Error -> WeatherState.Error(result.exception)
                is Result.Loading -> WeatherState.Loading
            }
        }
    }

    fun updateCoordinates(lat: Double, lon: Double) {
        fetchWeather(lat, lon)
    }
}
