package com.github.michaelbull.advent2024.day14

import com.github.michaelbull.advent2024.Puzzle
import com.github.michaelbull.advent2024.math.Vector2

object Day14 : Puzzle<RobotMap, Int>(day = 14) {

    override fun parse(lines: Sequence<String>): RobotMap {
        return lines.toRobotMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: RobotMap): Int {
        return input.safetyFactor(Vector2(101, 103), 100)
    }

    fun part2(input: RobotMap): Int {
        return input.secondsForEasterEgg(Vector2(101, 103))
    }
}
