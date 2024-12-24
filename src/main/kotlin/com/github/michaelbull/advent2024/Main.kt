package com.github.michaelbull.advent2024

import com.github.michaelbull.advent2024.day01.Day1
import com.github.michaelbull.advent2024.day02.Day2
import com.github.michaelbull.advent2024.day03.Day3
import com.github.michaelbull.advent2024.day04.Day4
import com.github.michaelbull.advent2024.day05.Day5
import com.github.michaelbull.advent2024.day06.Day6
import com.github.michaelbull.advent2024.day07.Day7
import com.github.michaelbull.advent2024.day08.Day8
import com.github.michaelbull.advent2024.day09.Day9
import com.github.michaelbull.advent2024.day10.Day10
import com.github.michaelbull.advent2024.day11.Day11
import com.github.michaelbull.advent2024.day12.Day12
import com.github.michaelbull.advent2024.day13.Day13
import com.github.michaelbull.advent2024.day14.Day14
import com.github.michaelbull.advent2024.day15.Day15
import com.github.michaelbull.advent2024.day16.Day16
import com.github.michaelbull.advent2024.day17.Day17
import com.github.michaelbull.advent2024.day18.Day18
import com.github.michaelbull.advent2024.day19.Day19
import com.github.michaelbull.advent2024.day20.Day20
import com.github.michaelbull.advent2024.day21.Day21
import com.github.michaelbull.advent2024.day22.Day22
import com.github.michaelbull.advent2024.day23.Day23
import com.github.michaelbull.advent2024.day24.Day24
import kotlin.time.measureTimedValue

fun main() {
    val puzzles = listOf(
        Day1,
        Day2,
        Day3,
        Day4,
        Day5,
        Day6,
        Day7,
        Day8,
        Day9,
        Day10,
        Day11,
        Day12,
        Day13,
        Day14,
        Day15,
        Day16,
        Day17,
        Day18,
        Day19,
        Day20,
        Day21,
        Day22,
        Day23,
        Day24,
    )

    for (puzzle in puzzles) {
        puzzle.solve()
    }
}

private fun <T : Any, V : Any> Puzzle<T, V>.solve() {
    println("")
    println("Day $day:")

    for ((index, solution) in solutions().withIndex()) {
        val (answer, duration) = measureTimedValue { solve(solution) }
        println("  part ${index + 1}:")
        println("    duration: $duration")
        println("    answer:   $answer")
    }
}
