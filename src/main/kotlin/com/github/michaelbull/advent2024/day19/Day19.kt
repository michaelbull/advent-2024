package com.github.michaelbull.advent2024.day19

import com.github.michaelbull.advent2024.Puzzle

object Day19 : Puzzle<Towel, Long>(day = 19) {

    override fun parse(lines: Sequence<String>): Towel {
        return lines.toTowel()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Towel): Long {
        return input.countWaysToDesign()
    }

    fun part2(input: Towel): Long {
        return input.sumWaysToDesign()
    }
}

