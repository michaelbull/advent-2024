package com.github.michaelbull.advent2024.day05

import com.github.michaelbull.advent2024.Puzzle

object Day5 : Puzzle<SafetyManual, Int>(day = 5) {

    override fun parse(lines: Sequence<String>): SafetyManual {
        return lines.toSafetyManual()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: SafetyManual): Int {
        return input.findOrderedUpdates().sumOf(Update::middle)
    }

    fun part2(input: SafetyManual): Int {
        return input.reorderIncorrectUpdates().sumOf(Update::middle)
    }
}

private val <T> List<T>.middleIndex: Int
    get() = (if (size % 2 == 0) (size / 2) - 1 else size / 2)

private fun <T> List<T>.middle(): T {
    return get(middleIndex)
}
