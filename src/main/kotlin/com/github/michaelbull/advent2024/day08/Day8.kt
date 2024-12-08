package com.github.michaelbull.advent2024.day08

import com.github.michaelbull.advent2024.Puzzle

object Day8 : Puzzle<CityMap, Int>(day = 8) {

    override fun parse(lines: Sequence<String>): CityMap {
        return lines.toCityMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: CityMap): Int {
        return input.travelOncePositions()
    }

    fun part2(input: CityMap): Int {
        return input.travelPositions()
    }
}

