package com.github.michaelbull.advent2024.day03

import com.github.michaelbull.advent2024.Puzzle

object Day3 : Puzzle<Sequence<Instruction>, Int>(day = 3) {

    override fun parse(lines: Sequence<String>): Sequence<Instruction> {
        return lines.flatMap(String::toInstructions)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<Instruction>): Int {
        val instructions = input.filterIsInstance<MultiplyInstruction>()

        return Program.run(instructions)
    }

    fun part2(input: Sequence<Instruction>): Int {
        return Program.run(input)
    }
}
