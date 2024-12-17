package com.github.michaelbull.advent2024.day17

fun Iterator<String>.nextRegisterFile(): RegisterFile {
    return RegisterFile(
        a = next().toRegister('A'),
        b = next().toRegister('B'),
        c = next().toRegister('C'),
    )
}

private val REGISTER_REGEX = """Register ([A-Z]): (\d+)""".toRegex()

private fun String.toRegister(register: Char): Long {
    val match = requireNotNull(REGISTER_REGEX.matchEntire(this)) {
        "$this must match $REGISTER_REGEX"
    }

    val (char, number) = match.destructured

    require(char.first() == register) {
        "$char must match $register"
    }

    return number.toLong()
}

data class RegisterFile(
    val a: Long,
    val b: Long,
    val c: Long,
)
