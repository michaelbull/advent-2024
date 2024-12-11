package com.github.michaelbull.advent2024.day11

import com.github.michaelbull.advent2024.Puzzle

object Day11 : Puzzle<StoneArrangement, Long>(day = 11) {

    override fun parse(lines: Sequence<String>): StoneArrangement {
        return lines.first().toStoneArrangement()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: StoneArrangement): Long {
        return input.blink(times = 25)
    }

    fun part2(input: StoneArrangement): Long {
        return input.blink(times = 75)
    }
}

