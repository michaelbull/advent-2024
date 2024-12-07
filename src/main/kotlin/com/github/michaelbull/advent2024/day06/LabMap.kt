package com.github.michaelbull.advent2024.day06

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.WEST
import com.github.michaelbull.advent2024.math.grid.CharGrid
import com.github.michaelbull.advent2024.math.grid.toCharGrid

fun Sequence<String>.toLabMap(): LabMap {
    return LabMap(toCharGrid())
}

class LabMap(private val grid: CharGrid) {

    fun guardPathLength(): Int {
        return path().positions.size
    }

    fun guardPathObstructionLoops(): Int {
        return path().positions
            .filter(::isVacantAt)
            .count(::pathLoopsWithObstruction)
    }

    private fun path(obstruction: Vector2? = null): Path {
        var position = findGuardPosition() ?: error("guard not found")
        var direction = SOUTH
        val positions = mutableSetOf<Vector2>()
        val steps = mutableSetOf<Step>()

        while (steps.add(Step(position, direction))) {
            positions += position

            val nextPosition = position + direction

            when {
                nextPosition !in grid -> return TerminalPath(positions)
                nextPosition == obstruction -> direction = direction.turn()
                isObstructionAt(nextPosition) -> direction = direction.turn()
                else -> position = nextPosition
            }
        }

        return LoopingPath(positions)
    }

    private fun pathLoopsWithObstruction(obstruction: Vector2): Boolean {
        return path(obstruction) is LoopingPath
    }

    private fun findGuardPosition(): Vector2? {
        return grid.positions().find(::isGuardAt)
    }

    private fun isVacantAt(position: Vector2): Boolean {
        return grid[position] == VACANT
    }

    private fun isGuardAt(position: Vector2): Boolean {
        return grid[position] == GUARD
    }

    private fun isObstructionAt(position: Vector2): Boolean {
        return grid[position] == OBSTRUCTION
    }

    private fun Vector2.turn(): Vector2 {
        return when (this) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
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
