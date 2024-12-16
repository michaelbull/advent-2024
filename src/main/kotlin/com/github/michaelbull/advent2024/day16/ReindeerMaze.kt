package com.github.michaelbull.advent2024.day16

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2024.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2024.math.grid.CharGrid
import com.github.michaelbull.advent2024.math.grid.toCharGrid
import java.util.PriorityQueue

fun Sequence<String>.toReindeerMaze(): ReindeerMaze {
    return ReindeerMaze(toCharGrid())
}

class ReindeerMaze(
    private val grid: CharGrid,
) {

    fun minScore(): Int {
        return dijkstra()
            .minBy(Reindeer::score)
            .score
    }

    fun tilesOnBestPath(): Int {
        return bestPaths()
            .flatMap(Reindeer::tiles)
            .distinct()
            .count()
    }

    private fun bestPaths(): Sequence<Reindeer> {
        val best = minScore()

        return dijkstra().takeWhile { reindeer ->
            reindeer.score <= best
        }
    }

    private fun dijkstra() = sequence {
        val start = tileOf('S')
        val end = tileOf('E')

        val root = Step(start, EAST)

        val scores = mutableMapOf<Step, Int>()
        val queue = PriorityQueue(compareBy(Reindeer::score))

        scores[root] = 0
        queue += Reindeer(root, listOf(start), 0)

        while (queue.isNotEmpty()) {
            val reindeer = queue.poll()

            if (reindeer.step.tile == end) {
                yield(reindeer)
            }

            val neighbours = reindeer.step.tile.neighbours()

            for (neighbour in neighbours) {
                val alt = reindeer.score + reindeer.scoreToMoveIn(neighbour.direction)
                val score = scores.getOrDefault(neighbour, Int.MAX_VALUE)

                if (alt <= score) {
                    scores[neighbour] = alt

                    queue += Reindeer(
                        step = neighbour,
                        tiles = reindeer.tiles + neighbour.tile,
                        score = alt,
                    )
                }
            }
        }
    }

    private fun tileOf(char: Char): Vector2 {
        return grid.first { it.second == char }.first
    }

    private fun Vector2.neighbours(): List<Step> {
        return CARDINAL_DIRECTIONS
            .map(::stepIn)
            .filter(grid::contains)
    }
}

private fun Vector2.stepIn(direction: Vector2): Step {
    return Step(this + direction, direction)
}

private operator fun CharGrid.contains(step: Step): Boolean {
    return step.tile in this && this[step.tile] != '#'
}
