package com.github.michaelbull.advent2024

import com.github.michaelbull.advent2024.day01.Day1
import com.github.michaelbull.advent2024.day02.Day2
import com.github.michaelbull.advent2024.day03.Day3
import com.github.michaelbull.advent2024.day04.Day4
import kotlin.time.measureTimedValue

fun main() {
    val puzzles = listOf(
        Day1,
        Day2,
        Day3,
        Day4,
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
