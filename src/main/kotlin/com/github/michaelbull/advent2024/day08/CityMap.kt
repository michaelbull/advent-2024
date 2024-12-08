package com.github.michaelbull.advent2024.day08

import com.github.michaelbull.advent2024.math.Vector2
import com.github.michaelbull.advent2024.math.grid.CharGrid
import com.github.michaelbull.advent2024.math.grid.toCharGrid
import com.github.michaelbull.itertools.pairPermutations

fun Sequence<String>.toCityMap(): CityMap {
    return CityMap(toCharGrid())
}

private typealias Path = Sequence<Vector2>
private typealias PathGenerator = (Pair<Vector2, Vector2>) -> Path

data class CityMap(
    private val grid: CharGrid,
) {

    fun travelOncePositions(): Int {
        return countPositionsBy(::travelOnce)
    }

    fun travelPositions(): Int {
        return countPositionsBy(::travel)
    }

    private fun countPositionsBy(generator: PathGenerator): Int {
        return antennaPositionsByFrequency().values
            .flatMap { positions -> positions.paths(generator) }
            .distinct()
            .size
    }

    private fun antennaPositionsByFrequency(): Map<Char, List<Vector2>> {
        return antennaPositions().groupBy(grid::get)
    }

    private fun antennaPositions(): List<Vector2> {
        return grid.positions().filterNot(::isEmpty)
    }

    private fun isEmpty(position: Vector2): Boolean {
        return grid[position] == '.'
    }

    private fun List<Vector2>.paths(generator: PathGenerator): Path {
        return pairPermutations().flatMap(generator)
    }

    private fun travelOnce(pair: Pair<Vector2, Vector2>): Path {
        return travel(pair).drop(1).take(1)
    }

    private fun travel(pair: Pair<Vector2, Vector2>) = sequence {
        val (first, second) = pair
        val distance = second - first
        var current = second

        while (current in grid) {
            yield(current)
            current += distance
        }
    }
}
