package com.github.michaelbull.advent2024.day21

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.grid.toCharGrid
import com.github.michaelbull.itertools.product
import kotlin.math.abs

fun Sequence<String>.toKeypad(): Keypad {
    return Keypad(toList())
}

private typealias Keycode = List<Char>
private typealias Keypair = Pair<Char, Char>

class Keypad(
    private val lines: List<String>,
) {

    private val keycodesByKeypair = KEYPAIRS.associateWith { keypair ->
        keypair.toKeycode()
    }

    fun sumComplexities(directionalKeypads: Int): Long {
        val shortest = minLengthByKeypair(directionalKeypads)

        return lines.sumOf { line ->
            shortest.complexity(line)
        }
    }

    private fun Map<Keypair, Long>.complexity(line: String): Long {
        val multiplier = line.dropLast(1).toInt()
        val length = minLength(line)
        return length * multiplier
    }

    private fun Map<Keypair, Long>.minLength(line: String): Long {
        val firstButton = line.takeLast(1).single()

        val (_, length) = line.fold(firstButton to 0L) { (button, length), nextButton ->
            val keycodes = keycodesBetween(
                start = numericalPositionOf(button),
                end = numericalPositionOf(nextButton),
                excluding = EMPTY_NUMERICAL_POSITIONS,
            )

            val nextLength = keycodes.minLengthIn(this)
            val totalLength = length + nextLength

            nextButton to totalLength
        }

        return length
    }

    private fun List<Keycode>.minLengthIn(minLengthByKeypair: Map<Keypair, Long>): Long {
        return minOf { keycode ->
            minLengthByKeypair.minLength(keycode)
        }
    }

    private fun Map<Keypair, Long>.minLength(keycode: Keycode): Long {
        return keycode.fold(ACTIVATE to 0L) { (button, length), nextButton ->
            val keypress = button to nextButton
            val prevLength = getOrDefault(keypress, 0)
            val nextLength = prevLength + length
            nextButton to nextLength
        }.second
    }

    private fun minLengthByKeypair(directionalKeypads: Int): Map<Keypair, Long> {
        val keypadRange = 1 until directionalKeypads

        val initial = keycodesByKeypair.mapValues { (_, keycodes) ->
            keycodes.minOf { it.size.toLong() }
        }

        return keypadRange.fold(initial) { acc, _ ->
            keycodesByKeypair.mapValues { (_, keycodes) ->
                keycodes.minLengthIn(acc)
            }
        }
    }

    private fun Keypair.toKeycode(): List<Keycode> {
        return keycodesBetween(
            start = directionalPositionOf(first),
            end = directionalPositionOf(second),
            excluding = EMPTY_DIRECTIONAL_POSITIONS,
        )
    }

    private fun keycodesBetween(start: Vector2, end: Vector2, excluding: List<Vector2>): List<Keycode> {
        return buildList {
            for (excluded in excluding) {
                if (start.x != excluded.x || end.y != excluded.y) {
                    add(verticalThenHorizontal(start, end))
                }

                if (end.x != excluded.x || start.y != excluded.y) {
                    add(horizontalThenVertical(start, end))
                }
            }
        }
    }

    private fun horizontalThenVertical(start: Vector2, end: Vector2): Keycode {
        return horizontalKeycode(start.x, end.x) +
            verticalKeycode(start.y, end.y) +
            ACTIVATE
    }

    private fun verticalThenHorizontal(start: Vector2, end: Vector2): Keycode {
        return verticalKeycode(start.y, end.y) +
            horizontalKeycode(start.x, end.x) +
            ACTIVATE
    }

    private fun horizontalButton(start: Int, end: Int): Char? {
        return when {
            start < end -> RIGHT
            start > end -> LEFT
            else -> null
        }
    }

    private fun verticalButton(start: Int, end: Int): Char? {
        return when {
            start < end -> DOWN
            start > end -> UP
            else -> null
        }
    }

    private fun horizontalKeycode(start: Int, end: Int): Keycode {
        return keycode(start, end, horizontalButton(start, end))
    }

    private fun verticalKeycode(start: Int, end: Int): Keycode {
        return keycode(start, end, verticalButton(start, end))
    }

    private fun keycode(start: Int, end: Int, button: Char?): Keycode {
        return if (button == null) {
            emptyList()
        } else {
            val size = abs(end - start)
            List(size) { button }
        }
    }

    private fun numericalPositionOf(char: Char): Vector2 {
        return NUMERICAL_GRID.single { it.second == char }.first
    }

    private fun directionalPositionOf(char: Char): Vector2 {
        return DIRECTIONAL_GRID.single { it.second == char }.first
    }

    private companion object {
        private const val NUMERIC_KEYPAD = """
            789
            456
            123
             0A
        """

        private const val DIRECTIONAL_KEYPAD = """
             ^A
            <v>
        """

        private const val UP = '^'
        private const val ACTIVATE = 'A'
        private const val LEFT = '<'
        private const val DOWN = 'v'
        private const val RIGHT = '>'

        private val DIRECTIONAL_BUTTONS = listOf(
            UP,
            ACTIVATE,
            LEFT,
            DOWN,
            RIGHT,
        )

        private val KEYPAIRS = DIRECTIONAL_BUTTONS.product(DIRECTIONAL_BUTTONS).toList()

        private val NUMERICAL_GRID = NUMERIC_KEYPAD.toCharGrid()
        private val DIRECTIONAL_GRID = DIRECTIONAL_KEYPAD.toCharGrid()

        private val EMPTY_NUMERICAL_POSITIONS = NUMERICAL_GRID.filter { it.second == ' ' }.map { it.first }
        private val EMPTY_DIRECTIONAL_POSITIONS = DIRECTIONAL_GRID.filter { it.second == ' ' }.map { it.first }
    }
}
