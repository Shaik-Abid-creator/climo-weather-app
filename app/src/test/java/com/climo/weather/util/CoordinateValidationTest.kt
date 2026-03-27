package com.climo.weather.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CoordinateValidationTest {

    @Test
    fun `returns parsed value when coordinate is in range`() {
        assertEquals(40.7128, parseCoordinateInput("40.7128", -90.0, 90.0), 0.0)
        assertEquals(-74.0060, parseCoordinateInput(" -74.0060 ", -180.0, 180.0), 0.0)
    }

    @Test
    fun `returns null when coordinate is not a number`() {
        assertNull(parseCoordinateInput("abc", -90.0, 90.0))
    }

    @Test
    fun `returns null when coordinate is out of range`() {
        assertNull(parseCoordinateInput("120.0", -90.0, 90.0))
        assertNull(parseCoordinateInput("-181.0", -180.0, 180.0))
    }
}
