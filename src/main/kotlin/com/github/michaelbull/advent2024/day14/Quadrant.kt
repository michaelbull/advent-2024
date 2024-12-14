package com.github.michaelbull.advent2024.day14

import com.github.michaelbull.advent2024.math.Vector2

/**
 * https://en.wikipedia.org/wiki/Quadrant_(plane_geometry)
 */
sealed interface Quadrant {
    data object I : Quadrant
    data object II : Quadrant
    data object III : Quadrant
    data object IV : Quadrant
}

fun Vector2.toQuadrantOrNull(): Quadrant? {
    return when (sign()) {
        Vector2.NORTH_EAST -> Quadrant.I
        Vector2.NORTH_WEST -> Quadrant.II
        Vector2.SOUTH_WEST -> Quadrant.III
        Vector2.SOUTH_EAST -> Quadrant.IV
        else -> null
    }
}
