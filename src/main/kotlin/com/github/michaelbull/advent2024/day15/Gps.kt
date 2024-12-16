package com.github.michaelbull.advent2024.day15

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.WEST
import com.github.michaelbull.advent2024.math.grid.CharGrid
import com.github.michaelbull.advent2024.math.grid.toCharGrid

fun Sequence<String>.toGps(): Gps {
    val iterator = iterator()

    val warehouse = iterator.asSequence()
        .takeWhile(String::isNotEmpty)
        .toCharGrid()

    val movements = iterator.asSequence()
        .flatMap { line -> line.map(Char::toVector2) }
        .toList()

    return Gps(warehouse, movements)
}

private fun Char.toVector2(): Vector2 {
    return when (this) {
        '^' -> SOUTH
        '>' -> EAST
        'v' -> NORTH
        '<' -> WEST
        else -> throw IllegalArgumentException()
    }
}

data class Gps(
    private val warehouse: CharGrid,
    private val movements: List<Vector2>,
) {

    fun simulate() {
        travel().forEach { (position, char) ->
            warehouse[position] = char
        }
    }

    fun widenWarehouse(): Gps {
        val widened = CharGrid(warehouse.width * 2, warehouse.height) { EMPTY }

        for ((position, char) in warehouse) {
            val (leftChar, rightChar) = char.widen()
            val (leftPosition, rightPosition) = position.widen()
            widened[leftPosition] = leftChar
            widened[rightPosition] = rightChar
        }

        return copy(warehouse = widened)
    }

    fun sumCoordinates(box: Char): Int {
        return warehouse.sumOf { (position, char) ->
            if (char == box) {
                position.coordinate()
            } else {
                0
            }
        }
    }

    private fun travel() = sequence<Pair<Vector2, Char>> {
        movements.fold(robotPosition()) { robot, movement ->
            val path = bfs(robot, movement)
            val noWalls = path.none { (_, next) -> isWallAt(next) }

            if (noWalls) {
                for ((curr, next) in path.toList().asReversed()) {
                    yield(next to warehouse[curr])
                    yield(curr to EMPTY)
                }

                robot + movement
            } else {
                robot
            }
        }
    }

    private fun bfs(start: Vector2, movement: Vector2) = sequence<Pair<Vector2, Vector2>> {
        val visited = mutableSetOf<Vector2>()
        val queue = ArrayDeque<Vector2>()

        visited += start
        queue += start

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            val next = curr + movement

            yield(curr to next)

            val adjacent = next.adjacent()
            queue += adjacent.filter(visited::add)
        }
    }

    private fun robotPosition(): Vector2 {
        return warehouse.positions().first { position ->
            isRobotAt(position)
        }
    }

    private fun isRobotAt(position: Vector2): Boolean {
        return warehouse[position] == ROBOT
    }

    private fun isWallAt(position: Vector2): Boolean {
        return warehouse[position] == WALL
    }

    private fun Vector2.coordinate(): Int {
        return x + (y * 100)
    }

    private fun Vector2.adjacent(): Set<Vector2> {
        return when (warehouse[this]) {
            EMPTY, WALL -> emptySet()
            BOX -> setOf(this)
            BOX_LEFT -> setOf(this, this + EAST)
            BOX_RIGHT -> setOf(this, this + WEST)
            else -> throw IllegalStateException()
        }
    }

    private fun Vector2.widen(): Pair<Vector2, Vector2> {
        return copy(x = x * 2) to copy(x = (x * 2) + 1)
    }

    private fun Char.widen(): Pair<Char, Char> {
        return when (this) {
            WALL -> WALL to WALL
            BOX -> BOX_LEFT to BOX_RIGHT
            EMPTY -> EMPTY to EMPTY
            ROBOT -> ROBOT to EMPTY
            else -> throw IllegalArgumentException()
        }
    }

    private companion object {
        private const val EMPTY = '.'
        private const val ROBOT = '@'
        private const val BOX = 'O'
        private const val WALL = '#'
        private const val BOX_LEFT = '['
        private const val BOX_RIGHT = ']'
    }
}
