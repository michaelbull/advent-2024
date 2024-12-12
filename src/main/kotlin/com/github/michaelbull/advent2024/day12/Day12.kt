package com.github.michaelbull.advent2024.day12

import com.github.michaelbull.advent2024.Puzzle

object Day12 : Puzzle<GardenPlotMap, Int>(day = 12) {

    override fun parse(lines: Sequence<String>): GardenPlotMap {
        return lines.toGardenPlotMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: GardenPlotMap): Int {
        return input.price()
    }

    fun part2(input: GardenPlotMap): Int {
        return input.discountPrice()
    }
}
