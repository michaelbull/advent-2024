package com.github.michaelbull.advent2024.day06

import com.github.michaelbull.advent2024.Puzzle

object Day6 : Puzzle<LabMap, Int>(day = 6) {

    override fun parse(lines: Sequence<String>): LabMap {
        return lines.toLabMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: LabMap): Int {
        return input.pathLength()
    }

    fun part2(input: LabMap): Int {
        return input.obstructionLoopPositions()
    }
}

