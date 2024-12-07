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
    return filter { it.toBoolean(operators.toList()) }
        .sumOf(Equation::testValue)
}

data class Equation(
    val testValue: Long,
    val values: List<Long>,
) {

    fun toBoolean(operators: List<Operator>): Boolean {
        return operators.any(values)
    }

    private fun List<Operator>.any(values: List<Long>): Boolean {
        return when {
            values.first() > testValue -> false
            values.drop(1).isEmpty() -> values.first() == testValue
            else -> any { operator -> any(operator(values)) }
        }
    }
}

private operator fun Operator.invoke(values: List<Long>): List<Long> {
    val value = this(values.first(), values.second())
    return listOf(value) + values.drop(2)
}

private fun <T> List<T>.second(): T {
    return if (isNotEmpty()) this[1] else throw NoSuchElementException("List is empty.")
}
