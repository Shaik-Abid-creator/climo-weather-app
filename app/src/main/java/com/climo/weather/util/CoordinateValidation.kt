package com.climo.weather.util

fun parseCoordinateInput(raw: String, min: Double, max: Double): Double? {
    val value = raw.trim().toDoubleOrNull() ?: return null
    return value.takeIf { it in min..max }
}
