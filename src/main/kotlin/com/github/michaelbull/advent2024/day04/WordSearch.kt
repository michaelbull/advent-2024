package com.github.michaelbull.advent2024.day04

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.DIRECTIONS
import com.github.michaelbull.advent2024.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH_EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH_WEST
import com.github.michaelbull.advent2024.math.Vector2.Companion.ORDINAL_DIRECTIONS
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH_EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH_WEST
import com.github.michaelbull.advent2024.math.Vector2.Companion.WEST
import com.github.michaelbull.advent2024.math.Vector2CharMap

data class WordSearch(
    val chars: Vector2CharMap,
) {

    fun count(word: String): Int {
        return chars.positions().sumOf { position ->
            word countStartingFrom position
        }
    }

    fun countCrosses(word: String): Int {
        return chars.positions().sumOf { position ->
            word countCrossingFrom position
        }
    }

    private infix fun String.countStartingFrom(position: Vector2): Int {
        return DIRECTIONS.count { direction ->
            spans(position, direction)
        }
    }

    private infix fun String.countCrossingFrom(position: Vector2): Int {
        return ORDINAL_DIRECTIONS.count { direction ->
            spans(position, direction) && crosses(position, direction)
        }
    }

    private fun String.spans(position: Vector2, direction: Vector2): Boolean {
        var current = position

        for (char in this) {
            if (chars.getOrDefault(current, Char.MIN_VALUE) == char) {
                current += direction
            } else {
                return false
            }
        }

        return true
    }

    private fun String.crosses(position: Vector2, direction: Vector2): Boolean {
        val cross = CROSSES.getOrElse(direction, ::emptyMap)

        return cross.any { (crossOffset, crossDirection) ->
            val crossPosition = position + (crossOffset * (length - 1))

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
