package com.github.michaelbull.advent2024.day24

import com.github.michaelbull.advent2024.Puzzle

object Day24 : Puzzle<Monitor, String>(day = 24) {

    override fun parse(lines: Sequence<String>): Monitor {
        return lines.toMonitor()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Monitor): String {
        return input.output(startingWith = 'z').toString()
    }

    fun part2(input: Monitor): String {
        return input.swappedWiresCsv()
    }
}

