package com.github.michaelbull.advent2024.day02

import com.github.michaelbull.advent2024.Puzzle

object Day2 : Puzzle<Sequence<Report>, Int>(day = 2) {

    override fun parse(lines: Sequence<String>): Sequence<Report> {
        return lines.map(String::toReport)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<Report>): Int {
        return input.count(Report::isSafe)
    }

    fun part2(input: Sequence<Report>): Int {
        return input.count(Report::isTolerable)
    }
}
