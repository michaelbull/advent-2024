package com.github.michaelbull.advent2024.day17

import com.github.michaelbull.advent2024.Puzzle

object Day17 : Puzzle<Computer, String>(day = 17) {

    override fun parse(lines: Sequence<String>): Computer {
        return lines.toComputer()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Computer): String {
        return input.outputs().joinToString(separator = ",")
    }

    fun part2(input: Computer): String {
        return input.minReplicatingRegisterA().toString()
    }
}

