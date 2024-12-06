package com.github.michaelbull.advent2024.day06

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.WEST
import com.github.michaelbull.advent2024.math.Vector2CharMap
import com.github.michaelbull.advent2024.math.toVector2CharMap

fun Sequence<String>.toLabMap(): LabMap {
    return LabMap(toVector2CharMap())
}

data class LabMap(
    val chars: Vector2CharMap,
) {

    fun pathLength(): Int {
        return guardPath().positions.size
    }

    fun obstructionLoopPositions(): Int {
        val vacantPositions = guardPath().positions.filter(::isVacantAt)

        return vacantPositions.count { position ->
            withObstruction(position).guardPath() is ClosedPath
        }
    }

    private fun guardPath(): Path {
        var position = guardPosition() ?: return EmptyPath
        var direction = SOUTH
        val positions = mutableSetOf<Vector2>()
        val steps = mutableSetOf<Step>()

        while (steps.add(Step(position, direction))) {
            positions += position

            val nextPosition = position + direction

            when (chars.getOrNull(nextPosition)) {
                VACANT, GUARD -> position = nextPosition
                OBSTRUCTION -> direction = direction.turn()
                null -> return OpenPath(positions, steps)
            }
        }

        return ClosedPath(positions, steps)
    }

    private fun withObstruction(position: Vector2): LabMap {
        val updated = chars.copy().apply {
            this[position] = OBSTRUCTION
        }

        return copy(
            chars = updated
        )
    }

    private fun guardPosition(): Vector2? {
        return chars.positions().find(::isGuardAt)
    }

    private fun isGuardAt(position: Vector2): Boolean {
        return chars[position] == GUARD
    }

    private fun isVacantAt(position: Vector2): Boolean {
        return chars[position] == VACANT
    }

    private fun Vector2.turn(): Vector2 {
        return when (this) {
            SOUTH -> EAST
            EAST -> NORTH
            NORTH -> WEST
            WEST -> SOUTH
            else -> throw IllegalArgumentException()
        }
    }

    private companion object {
        private const val VACANT = '.'
        private const val GUARD = '^'
        private const val OBSTRUCTION = '#'
    }
}
