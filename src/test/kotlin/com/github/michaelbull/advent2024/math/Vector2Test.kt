package com.github.michaelbull.advent2024.math

import kotlin.test.Test
import kotlin.test.assertEquals

class Vector2Test {

    @Test
    fun unaryPlus() {
        assertEquals(Vector2(1, 2), +Vector2(1, 2))
    }

    @Test
    fun plus() {
        assertEquals(Vector2(25, 17), Vector2(18, 10) + 7)
        assertEquals(Vector2(10, 15), Vector2(4, 10) + Vector2(6, 5))
    }

    @Test
    fun unaryMinus() {
        assertEquals(Vector2(-1, -2), -Vector2(1, 2))
    }

    @Test
    fun minus() {
        assertEquals(Vector2(44, 54), Vector2(50, 60) - 6)
        assertEquals(Vector2(12, 50), Vector2(20, 100) - Vector2(8, 50))
    }

    @Test
    fun times() {
        assertEquals(Vector2(12, 24), Vector2(4, 8) * 3)
        assertEquals(Vector2(144, 10), Vector2(12, 2) * Vector2(12, 5))
    }

    @Test
    fun div() {
        assertEquals(Vector2(10, 5), Vector2(30, 15) / 3)
        assertEquals(Vector2(12, 2), Vector2(144, 10) / Vector2(12, 5))
    }

    @Test
    fun cross() {
        assertEquals(-18, Vector2(2, 10) cross Vector2(5, 16))
    }

    @Test
    fun dot() {
        assertEquals(20, Vector2(1, 3) dot Vector2(2, 6))
    }

    @Test
    fun abs() {
        assertEquals(Vector2(10, 15), Vector2(-10, -15).abs())
    }
}
