package com.github.michaelbull.advent2024.day17

sealed interface Opcode

data object ADV : Opcode
data object BXL : Opcode
data object BST : Opcode
data object JNZ : Opcode
data object BXC : Opcode
data object OUT : Opcode
data object BDV : Opcode
data object CDV : Opcode

fun Int.toOpcode(): Opcode {
    return when (this) {
        0 -> ADV
        1 -> BXL
        2 -> BST
        3 -> JNZ
        4 -> BXC
        5 -> OUT
        6 -> BDV
        7 -> CDV
        else -> throw IllegalArgumentException()
    }
}
