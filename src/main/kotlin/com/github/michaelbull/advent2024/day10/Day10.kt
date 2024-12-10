package com.github.michaelbull.advent2024.day10

import com.github.michaelbull.advent2024.Puzzle

object Day10 : Puzzle<TopographicMap, Int>(day = 10) {

    override fun parse(lines: Sequence<String>): TopographicMap {
        return lines.toTopographicMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: TopographicMap): Int {
        return input.sumTrailheadScores()
    }

    fun part2(input: TopographicMap): Int {
        return input.sumTrailheadRatings()
    }
}

