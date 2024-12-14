package com.github.michaelbull.advent2024.day14

import com.github.michaelbull.advent2024.math.Vector2

fun Sequence<String>.toRobotMap(): RobotMap {
    return RobotMap(map(String::toRobot).toList())
}

class RobotMap(
    private val robots: List<Robot>,
) {

    fun safetyFactor(size: Vector2, seconds: Int): Int {
        val midpoint = size / 2

        val quadrantToCount = positionsAfter(size, seconds)
            .groupingBy { position -> position.quadrantRelativeTo(midpoint) }
            .eachCount()
            .filterNot { (quadrant, _) -> quadrant == null }

        return quadrantToCount.values.reduce(Int::times)
    }

    fun secondsForEasterEgg(size: Vector2): Int {
        val area = size.x * size.y
        val range = 0..<area

        return range.first { seconds ->
            positionsAfter(size, seconds).isDistinct()
        }
    }

    private fun positionsAfter(size: Vector2, seconds: Int): List<Vector2> {
        return robots.map { robot ->
            robot.positionAfter(seconds).within(size)
        }
    }

    private fun Vector2.within(size: Vector2): Vector2 {
        return ((this % size) + size) % size
    }

    private fun Robot.positionAfter(seconds: Int): Vector2 {
        val distance = velocity * seconds
        return position + distance
    }

    private fun Vector2.quadrantRelativeTo(origin: Vector2): Quadrant? {
        return (this - origin).toQuadrantOrNull()
    }

    private fun List<Vector2>.isDistinct(): Boolean {
        return size == toSet().size
    }
}
