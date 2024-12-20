package com.github.michaelbull.advent2024.day14

import com.github.michaelbull.advent2024.math.Direction.NORTH_EAST
import com.github.michaelbull.advent2024.math.Direction.NORTH_WEST
import com.github.michaelbull.advent2024.math.Direction.SOUTH_EAST
import com.github.michaelbull.advent2024.math.Direction.SOUTH_WEST
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
        NORTH_EAST.translation -> Quadrant.I
        NORTH_WEST.translation -> Quadrant.II
        SOUTH_WEST.translation -> Quadrant.III
        SOUTH_EAST.translation -> Quadrant.IV
        else -> null
    }
}
