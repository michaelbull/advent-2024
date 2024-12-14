package com.github.michaelbull.advent2024.day12

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2024.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2024.math.Vector2.Companion.NORTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2024.math.Vector2.Companion.WEST
import com.github.michaelbull.advent2024.math.grid.CharGrid
import com.github.michaelbull.advent2024.math.grid.toCharGrid

fun Sequence<String>.toGardenPlotMap(): GardenPlotMap {
    return GardenPlotMap(toCharGrid())
}

class GardenPlotMap(
    private val grid: CharGrid,
) {

    fun price(): Int {
        return totalPrice(::price)
    }

    fun discountPrice(): Int {
        return totalPrice(::discountPrice)
    }

    private inline fun totalPrice(price: (Vector2, MutableSet<Vector2>) -> Int): Int {
        val visited = mutableSetOf<Vector2>()
        var total = 0

        for (position in grid.positionsIterator()) {
            if (position !in visited) {
                total += price(position, visited)
            }
        }

        return total
    }

    private fun price(root: Vector2, visited: MutableSet<Vector2>): Int {
        val perimeters = bfs(root, visited)
            .map { (_, adjacent) -> 4 - adjacent.size }
            .toList()

        return perimeters.size * perimeters.sum()
    }

    private fun discountPrice(root: Vector2, visited: MutableSet<Vector2>): Int {
        val positions = bfs(root, visited)
            .map { (position, _) -> position }
            .toSet()

        return positions.size * positions.countExposedSides()
    }

    private fun bfs(root: Vector2, visited: MutableSet<Vector2>) = sequence {
        val queue = ArrayDeque<Vector2>()
        val plant = grid[root]

        visited += root
        queue += root

        while (queue.isNotEmpty()) {
            val position = queue.removeFirst()
            val adjacent = position.adjacentTo(plant)

            yield(position to adjacent)

            queue += adjacent.filter(visited::add)
        }
    }

    private fun Set<Vector2>.countExposedSides(): Int {
        return CARDINAL_DIRECTIONS.sumOf { direction ->
            countExposedSides(direction)
        }
    }

    private fun Set<Vector2>.countExposedSides(direction: Vector2): Int {
        val sides = sides(direction)
        val internalSides = sides.walk(direction.turn())
        return sides.size - internalSides.size
    }

    private fun Set<Vector2>.sides(direction: Vector2): Set<Vector2> {
        return map(direction::plus)
            .filterNot(::contains)
            .toSet()
    }

    private fun Set<Vector2>.walk(direction: Vector2): Set<Vector2> {
        return flatMapTo(mutableSetOf()) { position ->
            walk(position, direction)
        }
    }

    private fun Set<Vector2>.walk(position: Vector2, direction: Vector2): Sequence<Vector2> {
        return generateSequence(position + direction, direction::plus).takeWhile(::contains)
    }

    private fun Vector2.turn(): Vector2 {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            else -> throw IllegalArgumentException()
        }
    }

    private fun Vector2.adjacent(): List<Vector2> {
        return CARDINAL_DIRECTIONS.map(::plus)
    }

    private fun Vector2.adjacentTo(plant: Char): List<Vector2> {
        return adjacent().filter { it inRegionOf plant }
    }

    private infix fun Vector2.inRegionOf(plant: Char): Boolean {
        return this in grid && grid[this] == plant
    }
}