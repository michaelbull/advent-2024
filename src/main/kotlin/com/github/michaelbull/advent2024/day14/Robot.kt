package com.github.michaelbull.advent2024.day14

import com.github.michaelbull.advent2024.math.Vector2

private val REGEX = """p=(\d+),(\d+) v=(-?\d+),(-?\d+)""".toRegex()

fun String.toRobot(): Robot {
    val match = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (positionX, positionY, velocityX, velocityY) = match.destructured

    return Robot(
        position = Vector2(positionX.toInt(), positionY.toInt()),
        velocity = Vector2(velocityX.toInt(), velocityY.toInt()),
    )
}

data class Robot(
    val position: Vector2,
    val velocity: Vector2,
)
