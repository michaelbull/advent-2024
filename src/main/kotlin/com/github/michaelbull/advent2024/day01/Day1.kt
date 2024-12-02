package com.github.michaelbull.advent2024.day01

import com.github.michaelbull.advent2024.Puzzle
import kotlin.math.abs

private typealias IntPair = Pair<Int, Int>
private typealias IntListPair = Pair<List<Int>, List<Int>>

private fun String.toIntPair(): IntPair {
    val (left, right) = split("   ", limit = 2)
    return left.toInt() to right.toInt()
}

private fun IntPair.difference(): Int {
    return abs(first - second)
}

private fun Map<Int, Int>.similarityScore(element: Int): Int {
    return element * getOrDefault(element, 0)
}

object Day1 : Puzzle<IntListPair, Int>(day = 1) {

    override fun parse(lines: Sequence<String>): IntListPair {
        val pairs = lines.map(String::toIntPair).toList()
        val left = pairs.map(IntPair::first)
        val right = pairs.map(IntPair::second)
        return left to right
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: IntListPair): Int {
        val (left, right) = input

        return left.sorted()
            .zip(right.sorted())
            .sumOf(IntPair::difference)
    }

    fun part2(input: IntListPair): Int {
        val (left, right) = input
        val frequencies = right.groupingBy { it }.eachCount()

        return left.sumOf(frequencies::similarityScore)
    }
}
