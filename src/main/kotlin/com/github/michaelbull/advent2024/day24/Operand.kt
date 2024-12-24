package com.github.michaelbull.advent2024.day24

sealed interface Operand

data object And : Operand
data object Or : Operand
data object Xor : Operand

fun String.toOperand(): Operand {
    return when (this) {
        "AND" -> And
        "OR" -> Or
        "XOR" -> Xor
        else -> throw IllegalArgumentException()
    }
}
