package com.github.michaelbull.advent2024.day25

import com.github.michaelbull.advent2024.math.grid.BooleanGrid

fun Sequence<String>.toLockpick(): Lockpick {
    val locks = mutableListOf<BooleanGrid>()
    val keys = mutableListOf<BooleanGrid>()

    for (window in chunked(8)) {
        val lines = window.filter(String::isNotEmpty)
        val first = lines.first()
        val last = lines.last()

        val collection = when {
            first.all { it == '#' } && last.all { it == '.' } -> locks
            first.all { it == '.' } && last.all { it == '#' } -> keys
            else -> throw IllegalArgumentException()
        }

        val body = lines.drop(1).dropLast(1)
        val grid = BooleanGrid(width = first.length, height = body.size)

        for ((y, line) in body.withIndex()) {
            for ((x, char) in line.withIndex()) {
                grid[x, y] = char == '#'
            }
        }

        collection += grid
    }

    return Lockpick(locks, keys)
}

class Lockpick(
    private val locks: List<BooleanGrid>,
    private val keys: List<BooleanGrid>,
) {

    fun countDistinct(): Int {
        val gridHeight = locks.first().height

        infix fun List<Int>.fits(other: List<Int>): Boolean {
            return withIndex().all { (column, height) ->
                height + other[column] <= gridHeight
            }
        }

        return keys.map(BooleanGrid::heights).sumOf { key ->
            locks.map(BooleanGrid::heights).count { lock ->
                key fits lock
            }
        }
    }
}

private fun BooleanGrid.heights(): List<Int> {
    return xRange.map { x ->
        yRange.count { y ->
            this[x, y]
        }
    }
}
