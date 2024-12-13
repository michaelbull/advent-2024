package com.github.michaelbull.advent2024.day13

import com.github.michaelbull.advent2024.Puzzle

object Day13 : Puzzle<Sequence<ClawMachine>, Long>(day = 13) {

    override fun parse(lines: Sequence<String>): Sequence<ClawMachine> {
        return lines.chunked(4).map(List<String>::toClawMachine)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<ClawMachine>): Long {
        return input.sumOf(ClawMachine::tokenCost)
    }

    fun part2(input: Sequence<ClawMachine>): Long {
        return input.sumOf { machine ->
            machine.corrected().tokenCost()
        }
    }
}
