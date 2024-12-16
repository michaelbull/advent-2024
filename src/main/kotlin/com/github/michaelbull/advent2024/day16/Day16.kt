package com.github.michaelbull.advent2024.day16

import com.github.michaelbull.advent2024.Puzzle

object Day16 : Puzzle<ReindeerMaze, Int>(day = 16) {

    override fun parse(lines: Sequence<String>): ReindeerMaze {
        return lines.toReindeerMaze()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: ReindeerMaze): Int {
        return input.minScore()
    }

    fun part2(input: ReindeerMaze): Int {
        return input.tilesOnBestPath()
    }
}
