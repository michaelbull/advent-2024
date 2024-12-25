package com.github.michaelbull.advent2024.day25

import com.github.michaelbull.advent2024.Puzzle

object Day25 : Puzzle<Lockpick, Int>(day = 25) {

    override fun parse(lines: Sequence<String>): Lockpick {
        return lines.toLockpick()
    }

    override fun solutions() = listOf(
        ::part1,
    )

    fun part1(input: Lockpick): Int {
        return input.countDistinct()
    }
}

