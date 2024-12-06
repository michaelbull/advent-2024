package com.github.michaelbull.advent2024.day04

import com.github.michaelbull.advent2024.Puzzle
import com.github.michaelbull.advent2024.math.grid.toCharGrid

object Day4 : Puzzle<WordSearch, Int>(day = 4) {

    override fun parse(lines: Sequence<String>): WordSearch {
        return WordSearch(lines.toCharGrid())
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: WordSearch): Int {
        return input.count("XMAS")
    }

    fun part2(input: WordSearch): Int {
        return input.countCrosses("MAS") / 2
    }
}
