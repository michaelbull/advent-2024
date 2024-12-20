package com.github.michaelbull.advent2024.day20

import com.github.michaelbull.advent2024.Puzzle

object Day20 : Puzzle<RacetrackMap, Int>(day = 20) {

    override fun parse(lines: Sequence<String>): RacetrackMap {
        return lines.toRacetrackMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: RacetrackMap): Int {
        return input.countSaves(100, 2)
    }

    fun part2(input: RacetrackMap): Int {
        return input.countSaves(100, 20)
    }
}

