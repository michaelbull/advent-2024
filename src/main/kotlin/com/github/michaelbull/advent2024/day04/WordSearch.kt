package com.github.michaelbull.advent2024.day04

import com.github.michaelbull.advent2024.math.Direction
import com.github.michaelbull.advent2024.math.Direction.EAST
import com.github.michaelbull.advent2024.math.Direction.NORTH
import com.github.michaelbull.advent2024.math.Direction.NORTH_EAST
import com.github.michaelbull.advent2024.math.Direction.NORTH_WEST
import com.github.michaelbull.advent2024.math.Direction.SOUTH
import com.github.michaelbull.advent2024.math.Direction.SOUTH_EAST
import com.github.michaelbull.advent2024.math.Direction.SOUTH_WEST
import com.github.michaelbull.advent2024.math.Direction.WEST
import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.grid.CharGrid

data class WordSearch(
    val grid: CharGrid,
) {

    fun count(word: String): Int {
        return grid.positions().sumOf { position ->
            word countStartingFrom position
        }
    }

    fun countCrosses(word: String): Int {
        return grid.positions().sumOf { position ->
            word countCrossingFrom position
        }
    }

    private infix fun String.countStartingFrom(position: Vector2): Int {
        return Direction.entries.count { direction ->
            spans(position, direction)
        }
    }

    private infix fun String.countCrossingFrom(position: Vector2): Int {
        return Direction.INTERCARDINALS.count { direction ->
            spans(position, direction) && crosses(position, direction)
        }
    }

    private fun String.spans(position: Vector2, direction: Direction): Boolean {
        var current = position

        for (char in this) {
            if (grid.getOrDefault(current, Char.MIN_VALUE) == char) {
                current += direction.translation
            } else {
                return false
            }
        }

        return true
    }

    private fun String.crosses(position: Vector2, direction: Direction): Boolean {
        val cross = CROSSES.getOrElse(direction, ::emptyMap)

        return cross.any { (crossOffset, crossDirection) ->
            val crossPosition = position + (crossOffset.translation * (length - 1))

            spans(crossPosition, crossDirection)
        }
    }

    private companion object {
        private val CROSSES = mapOf(
            NORTH_EAST to mapOf(
                NORTH to SOUTH_EAST,
                EAST to NORTH_WEST,
            ),

            NORTH_WEST to mapOf(
                NORTH to SOUTH_WEST,
                WEST to NORTH_EAST,
            ),

            SOUTH_EAST to mapOf(
                SOUTH to NORTH_EAST,
                EAST to SOUTH_WEST,
            ),

            SOUTH_WEST to mapOf(
                SOUTH to NORTH_WEST,
                WEST to SOUTH_EAST,
            ),
        )
    }
}
