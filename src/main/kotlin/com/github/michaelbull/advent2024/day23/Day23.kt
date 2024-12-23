package com.github.michaelbull.advent2024.day23

import com.github.michaelbull.advent2024.Puzzle

object Day23 : Puzzle<Network, String>(day = 23) {

    override fun parse(lines: Sequence<String>): Network {
        return lines.toNetwork()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Network): String {
        return input.countStartsWith('t')
    }

    fun part2(input: Network): String {
        return input.password()
    }
}

