package com.github.michaelbull.advent2024.day11

import kotlin.math.log10
import kotlin.math.pow

private typealias StoneCountMap = Map<Long, Long>
private typealias MutableStoneCountMap = MutableMap<Long, Long>

fun String.toStoneArrangement(): StoneArrangement {
    return StoneArrangement(split(" ").map(String::toLong))
}

class StoneArrangement(
    stones: List<Long>,
) {

    private val stoneCounts = stones.associateWith { 1L }

    fun blink(times: Int): Long {
        return blink().elementAt(times).values.sum()
    }

    private fun blink(): Sequence<StoneCountMap> {
        return generateSequence(stoneCounts, ::blink)
    }

    private fun blink(stoneCounts: StoneCountMap): StoneCountMap {
        val result = mutableMapOf<Long, Long>()

        for ((stone, count) in stoneCounts) {
            result.blink(stone, count)
        }

        return result
    }

    private fun MutableStoneCountMap.blink(stone: Long, count: Long) {
        val digits = stone.digitCount()

        when {
            stone == 0L -> mergeAdditively(1L, count)
            digits.isEven() -> split(stone, count)
            else -> mergeAdditively(stone * 2024, count)
        }
    }

    private fun MutableStoneCountMap.split(stone: Long, count: Long) {
        val divisor = 10.0.pow(stone.digitCount() / 2).toLong()
        val left = stone % divisor
        val right = stone / divisor

        mergeAdditively(left, count)
        mergeAdditively(right, count)
    }

    private fun MutableStoneCountMap.mergeAdditively(stone: Long, count: Long) {
        merge(stone, count, Long::plus)
    }

    private fun Long.digitCount(): Int {
        return log10(toDouble()).toInt() + 1
    }

    private fun Int.isEven(): Boolean {
        return this % 2 == 0
    }
}
