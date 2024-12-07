package com.github.michaelbull.advent2024.day07

import com.github.michaelbull.advent2024.Puzzle

object Day7 : Puzzle<Sequence<Equation>, Long>(day = 7) {

    override fun parse(lines: Sequence<String>): Sequence<Equation> {
        return lines.map(String::toEquation)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<Equation>): Long {
        return input.totalCalibrationResult(
            Long::plus,
            Long::times,
        )
    }

    fun part2(input: Sequence<Equation>): Long {
        return input.totalCalibrationResult(
            Long::plus,
            Long::times,
            Long::concat,
        )
    }
}

private infix fun Long.concat(other: Long): Long {
    return (toString() + other.toString()).toLong()
}
