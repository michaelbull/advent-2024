package com.github.michaelbull.advent2024.day07

typealias Operator = (Long, Long) -> Long

fun String.toEquation(): Equation {
    val (testValue, values) = split(": ", limit = 2)

    return Equation(
        testValue = testValue.toLong(),
        values = values.split(" ").map(String::toLong),
    )
}

fun Sequence<Equation>.totalCalibrationResult(vararg operators: Operator): Long {
    val operators = operators.toList()
    return filter { it.isValid(operators) }.sumOf(Equation::testValue)
}

data class Equation(
    val testValue: Long,
    val values: List<Long>,
) {

    fun isValid(operators: List<Operator>): Boolean {
        return isValid(operators, values.first(), 0)
    }

    private fun isValid(operators: List<Operator>, currentResult: Long, index: Int): Boolean {
        fun isNextValid(operator: Operator): Boolean {
            val nextIndex = index + 1
            val nextValue = values[nextIndex]
            val nextResult = operator(currentResult, nextValue)
            return isValid(operators, nextResult, nextIndex)
        }

        return when {
            currentResult > testValue -> false
            index == values.lastIndex -> currentResult == testValue
            else -> operators.any(::isNextValid)
        }
    }
}
