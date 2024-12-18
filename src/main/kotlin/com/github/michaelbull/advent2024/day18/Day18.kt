package com.github.michaelbull.advent2024.day18

import com.github.michaelbull.advent2024.Puzzle

object Day18 : Puzzle<MemorySpace, String>(day = 18) {

    override fun parse(lines: Sequence<String>): MemorySpace {
        return lines.toMemorySpace()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: MemorySpace): String {
        return input.minStepsToExit(corrupted = 1024, coordinates = 70).toString()
    }

    fun part2(input: MemorySpace): String {
        val (x, y) = input.firstUnreachableByte(coordinates = 70)
        return "$x,$y"
    }
}

