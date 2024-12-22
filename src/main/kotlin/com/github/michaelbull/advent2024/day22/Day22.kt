package com.github.michaelbull.advent2024.day22

import com.github.michaelbull.advent2024.Puzzle

object Day22 : Puzzle<Market, Long>(day = 22) {

    override fun parse(lines: Sequence<String>): Market {
        return lines.toMarket()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Market): Long {
        return input.sumSecretNumbers(2000)
    }

    fun part2(input: Market): Long {
        return input.maxBananas(2000)
    }
}

