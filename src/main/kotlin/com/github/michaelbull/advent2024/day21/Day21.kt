package com.github.michaelbull.advent2024.day21

import com.github.michaelbull.advent2024.Puzzle

object Day21 : Puzzle<Keypad, Long>(day = 21) {

    override fun parse(lines: Sequence<String>): Keypad {
        return lines.toKeypad()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Keypad): Long {
        return input.sumComplexities(2)
    }

    fun part2(input: Keypad): Long {
        return input.sumComplexities(25)
    }
}

