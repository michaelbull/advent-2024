package com.github.michaelbull.advent2024.day01

import com.github.michaelbull.advent2024.Puzzle
import kotlin.math.abs

private typealias IntPair = Pair<Int, Int>
private typealias IntListPair = Pair<List<Int>, List<Int>>

private fun String.toIntPair(): IntPair {
    val parts = split("   ")
    return parts[0].toInt() to parts[1].toInt()
}

private fun IntPair.difference(): Int {
    return abs(first - second)
}

private fun <T> Iterable<T>.countOf(element: T): Int = count { other ->
    element == other
}

private fun List<Int>.similarityScore(element: Int): Int {
    return element * countOf(element)
}

object Day1 : Puzzle<IntListPair, Int>(day = 1) {

    override fun parse(lines: Sequence<String>): IntListPair {
        val pairs = lines.map(String::toIntPair).toList()
        val left = pairs.map(IntPair::first).sorted()
        val right = pairs.map(IntPair::second).sorted()
        return left to right
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: IntListPair): Int {
        val (left, right) = input

        return left.zip(right).sumOf(IntPair::difference)
    }

    fun part2(input: IntListPair): Int {
        val (left, right) = input

        return left.sumOf(right::similarityScore)
    }
}
