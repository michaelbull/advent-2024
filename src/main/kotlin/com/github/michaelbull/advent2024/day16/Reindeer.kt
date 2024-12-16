package com.github.michaelbull.advent2024.day16

import com.github.michaelbull.advent2024.math.Vector2

data class Reindeer(
    val step: Step,
    val tiles: List<Vector2>,
    val score: Int,
) {
    fun scoreToMoveIn(direction: Vector2): Int {
        val turning = direction != step.direction
        return if (turning) 1001 else 1
    }
}
