package com.github.michaelbull.advent2024.day16

import com.github.michaelbull.advent2024.math.Direction
import com.github.michaelbull.advent2024.math.Direction.EAST
import com.github.michaelbull.advent2024.math.Vector2
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

            val neighbors = reindeer.step.tile.neighbors()

            for (neighbor in neighbors) {
                val alt = reindeer.score + reindeer.scoreToMoveIn(neighbor.direction)
                val score = scores.getOrDefault(neighbor, Int.MAX_VALUE)

                if (alt <= score) {
                    scores[neighbor] = alt

                    queue += Reindeer(
                        step = neighbor,
                        tiles = reindeer.tiles + neighbor.tile,
                        score = alt,
                    )
                }
            }
        }
    }

    private fun tileOf(char: Char): Vector2 {
        return grid.first { it.second == char }.first
    }

    private fun Vector2.neighbors(): List<Step> {
        return Direction.CARDINALS
            .map(::stepIn)
            .filter(grid::contains)
    }
}

private fun Vector2.stepIn(direction: Direction): Step {
    return Step(this + direction.translation, direction)
}

private operator fun CharGrid.contains(step: Step): Boolean {
    return step.tile in this && this[step.tile] != '#'
}
