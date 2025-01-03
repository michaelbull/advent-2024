package com.github.michaelbull.advent2024.day20

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.distance.manhattanDistance
import com.github.michaelbull.advent2024.math.grid.CharGrid
import com.github.michaelbull.advent2024.math.grid.toCharGrid
import com.github.michaelbull.advent2024.math.orthogonals
import com.github.michaelbull.advent2024.math.range.symmetricRange
import com.github.michaelbull.advent2024.math.toVector2
import com.github.michaelbull.itertools.product
import java.util.PriorityQueue

fun Sequence<String>.toRacetrackMap(): RacetrackMap {
    return RacetrackMap(toCharGrid())
}

class RacetrackMap(
    private val grid: CharGrid,
) {

    fun countSaves(minDuration: Int, maxCheatDuration: Int): Int {
        val start = startPosition()
        val distances = dijkstra(start)

        fun saves(cheat: Map.Entry<Vector2, Int>): Int {
            return distances.entries
                .mapNotNull { distances.getWithCheat(it, cheat) }
                .count { it >= minDuration }
        }

        return maxCheatDuration.translations().entries.sumOf(::saves)
    }

    private fun dijkstra(start: Vector2): Map<Vector2, Int> {
        val distances = mutableMapOf<Vector2, Int>()
        val queue = PriorityQueue(compareBy(distances::get))

        distances[start] = 0
        queue += start

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val neighbors = current.orthogonals().filterNot(::isWallAt)

            for (neighbor in neighbors) {
                val alt = distances[current]!! + 1
                val distance = distances.getOrDefault(neighbor, Int.MAX_VALUE)

                if (alt < distance) {
                    distances[neighbor] = alt
                    queue += neighbor
                }
            }
        }

        return distances
    }

    private fun isWallAt(position: Vector2): Boolean {
        return grid[position] == '#'
    }

    private fun startPosition(): Vector2 {
        return grid.first { it.second == 'S' }.first
    }

    private fun Map<Vector2, Int>.getWithCheat(current: Map.Entry<Vector2, Int>, cheat: Map.Entry<Vector2, Int>): Int? {
        val distance = getOrElse(current.key + cheat.key) { return null }
        return current.value - cheat.value - distance
    }

    private fun Int.translations(): Map<Vector2, Int> {
        return symmetricRange()
            .toVector2Product()
            .associateWith(Vector2::manhattanDistance)
            .filter { it.value <= this }
    }

    private fun IntRange.toVector2Product(): Sequence<Vector2> {
        return product(this).map(Pair<Int, Int>::toVector2)
    }
}

