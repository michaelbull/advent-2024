package com.github.michaelbull.advent2024.day17

private val PROGRAM_REGEX = """Program: (.*?)""".toRegex()

fun String.toProgram(): List<Int> {
    val match = requireNotNull(PROGRAM_REGEX.matchEntire(this)) {
        "$this must match $PROGRAM_REGEX"
    }

    val (numbers) = match.destructured

    return numbers.split(",").map(String::toThreeBitInt)
}

private fun String.toThreeBitInt(): Int {
    return this.toInt().also(::requireThreeBit)
}

private fun requireThreeBit(int: Int) {
    require(int in 0..7) {
        "$int is not 3-bit"
    }
}
