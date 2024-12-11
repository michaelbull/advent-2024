package com.github.michaelbull.advent2024.day10

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2024.math.grid.IntGrid
import com.github.michaelbull.advent2024.math.grid.toIntGrid

fun Sequence<String>.toTopographicMap(): TopographicMap {
    return TopographicMap(toIntGrid())
}

class TopographicMap(
    private val grid: IntGrid,
) {

    fun sumTrailheadScores(): Int {
        return trailheads().sumOf(::score)
    }

    fun sumTrailheadRatings(): Int {
        return trailheads().sumOf(::rating)
    }

    private fun score(trailhead: Vector2): Int {
        return bfs(trailhead).distinct().count()
    }

    private fun rating(trailhead: Vector2): Int {
        return bfs(trailhead).count()
    }

    private fun bfs(trailhead: Vector2) = sequence {
        val queue = ArrayDeque<Vector2>()

        queue += trailhead

        while (queue.isNotEmpty()) {
            val position = queue.removeFirst()

            if (isMaxHeight(position)) {
                yield(position)
            } else {
                queue += position.uphillSteps()
            }
        }
    }

    private fun Vector2.uphillSteps(): List<Vector2> {
        return adjacent().filter { it isUphillFrom this }
    }

    private fun Vector2.adjacent(): List<Vector2> {
        return CARDINAL_DIRECTIONS.map(::plus)
    }

    private infix fun Vector2.isUphillFrom(position: Vector2): Boolean {
        return this in grid && grid[this] == grid[position] + 1
    }

    private fun trailheads(): List<Vector2> {
        return grid.positions().filter(::isMinHeight)
    }

    private fun isMinHeight(position: Vector2): Boolean {
        return grid[position] == 0
    }

    private fun isMaxHeight(position: Vector2): Boolean {
        return grid[position] == 9
    }
}
