package com.github.michaelbull.advent2024.day18

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2024.math.safeMidpoint
import java.util.PriorityQueue

fun Sequence<String>.toMemorySpace(): MemorySpace {
    return MemorySpace(map(String::toVector2).toList())
}

private fun String.toVector2(): Vector2 {
    val (x, y) = split(",", limit = 2)
    return Vector2(x.toInt(), y.toInt())
}

class MemorySpace(
    private val bytes: List<Vector2>,
) {

    fun minStepsToExit(corrupted: Int, coordinates: Int): Int {
        return dijkstra(bytes.take(corrupted), coordinates).first()
    }

    fun firstUnreachableByte(coordinates: Int): Vector2 {
        val index = binarySearch(coordinates)
        return bytes[index]
    }

    private fun dijkstra(corrupted: List<Vector2>, coordinates: Int) = sequence {
        val range = Vector2.ZERO..Vector2(coordinates, coordinates)

        val distances = mutableMapOf<Vector2, Int>()
        val queue = PriorityQueue(compareBy(distances::get))

        distances[range.start] = 0
        queue += range.start

        while (queue.isNotEmpty()) {
            val position = queue.poll()

            if (position == range.endInclusive) {
                yield(distances[position]!!)
            }

            val neighbours = position.adjacent().filter {
                it in range && it !in corrupted
            }

            for (neighbour in neighbours) {
                val alt = distances[position]!! + 1
                val distance = distances.getOrDefault(neighbour, Int.MAX_VALUE)

                if (alt < distance) {
                    distances[neighbour] = alt
                    queue += neighbour
                }
            }
        }
    }

    private tailrec fun binarySearch(coordinates: Int, left: Int = 0, right: Int = bytes.size - 1): Int {
        return when {
            left > right -> throw IllegalArgumentException()
            left == right -> left
            else -> {
                val midpoint = safeMidpoint(left, right)
                val corrupted = bytes.take(midpoint)

                if (dijkstra(corrupted, coordinates).count() > 0) {
                    binarySearch(coordinates, midpoint + 1, right)
                } else {
                    binarySearch(coordinates, left, midpoint - 1)
                }
            }
        }
    }


    private fun Vector2.adjacent(): List<Vector2> {
        return CARDINAL_DIRECTIONS.map(::plus)
    }
}
