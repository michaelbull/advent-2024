package com.github.michaelbull.advent2024.day02

import kotlin.math.abs

fun String.toReport(): Report {
    val levels = split(" ").map(String::toInt)

    return Report(levels)
}

data class Report(
    val levels: List<Int>,
) {

    fun isSafe(): Boolean {
        return levels.isSafe()
    }

    fun isTolerable(): Boolean {
        return levels.indices
            .map(levels::dropAt)
            .any(List<Int>::isSafe)
    }
}

private fun <T> List<T>.dropAt(index: Int): List<T> {
    return subList(0, index) + subList(index + 1, size)
}

private fun List<Int>.isSafe(): Boolean {
    val pairs = zipWithNext()
    val progresses = pairs.all { it.increasing() } || pairs.all { it.decreasing() }
    val differs = pairs.all { it.differs() }
    return progresses && differs
}

private fun <T : Comparable<T>> Pair<T, T>.increasing(): Boolean {
    return second > first
}

private fun <T : Comparable<T>> Pair<T, T>.decreasing(): Boolean {
    return second < first
}

private fun Pair<Int, Int>.differs(): Boolean {
    return abs(second - first) in 1..3
}
