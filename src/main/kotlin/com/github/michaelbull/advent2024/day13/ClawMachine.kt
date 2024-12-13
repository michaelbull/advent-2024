package com.github.michaelbull.advent2024.day13

private val BUTTON_REGEX = """Button ([AB]): X\+(\d+), Y\+(\d+)""".toRegex()
private val PRIZE_REGEX = """Prize: X=(\d+), Y=(\d+)""".toRegex()

private fun String.toButtonBehaviour(button: Char): Pair<Long, Long> {
    val result = requireNotNull(BUTTON_REGEX.matchEntire(this)) {
        "$this must match $BUTTON_REGEX"
    }

    val (char, x, y) = result.destructured

    require(button == char.single()) {
        "$char must match $button"
    }

    return Pair(x.toLong(), y.toLong())
}

private fun String.toPrizeLocation(): Pair<Long, Long> {
    val result = requireNotNull(PRIZE_REGEX.matchEntire(this)) {
        "$this must match $PRIZE_REGEX"
    }

    val (x, y) = result.destructured

    return Pair(x.toLong(), y.toLong())
}

fun List<String>.toClawMachine(): ClawMachine {
    val (first, second, third) = this

    return ClawMachine(
        a = first.toButtonBehaviour('A'),
        b = second.toButtonBehaviour('B'),
        prize = third.toPrizeLocation(),
    )
}

data class ClawMachine(
    val a: Pair<Long, Long>,
    val b: Pair<Long, Long>,
    val prize: Pair<Long, Long>,
) {

    fun corrected(): ClawMachine {
        return copy(prize = prize.corrected())
    }

    fun tokenCost(): Long {
        val denominator = a determinantWith b

        return if (denominator == 0L) {
            0
        } else {
            val numeratorA = prize determinantWith b
            val numeratorB = a determinantWith prize

            val countA = numeratorA.divideExactlyOrElse(denominator) { return 0 }
            val countB = numeratorB.divideExactlyOrElse(denominator) { return 0 }

            (3 * countA) + countB
        }
    }

    private infix fun Pair<Long, Long>.determinantWith(other: Pair<Long, Long>): Long {
        val (x1, y1) = this
        val (x2, y2) = other
        return (x1 * y2) - (y1 * x2)
    }

    private inline fun Long.divideExactlyOrElse(other: Long, defaultValue: () -> Long): Long {
        val remainder = this % other

        return if (remainder == 0L) {
            this / other
        } else {
            defaultValue()
        }
    }

    private fun Pair<Long, Long>.corrected(): Pair<Long, Long> {
        return copy(
            first = first + PRIZE_CORRECTION,
            second = second + PRIZE_CORRECTION,
        )
    }

    private companion object {
        private const val PRIZE_CORRECTION = 10_000_000_000_000
    }
}
