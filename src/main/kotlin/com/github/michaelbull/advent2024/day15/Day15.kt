package com.github.michaelbull.advent2024.day15

import com.github.michaelbull.advent2024.Puzzle

object Day15 : Puzzle<Gps, Int>(day = 15) {

    override fun parse(lines: Sequence<String>): Gps {
        return lines.toGps()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Gps): Int {
        input.simulate()
        return input.sumCoordinates('O')
    }

    fun part2(input: Gps): Int {
        val widened = input.widenWarehouse()
        widened.simulate()
        return widened.sumCoordinates('[')
    }
}

