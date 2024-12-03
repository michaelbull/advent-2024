package com.github.michaelbull.advent2024.day03

private val MULTIPLY_REGEX = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
private val DO_REGEX = """do\(\)""".toRegex()
private val DONT_REGEX = """don't\(\)""".toRegex()

private val INSTRUCTION_REGEXES = listOf(
    MULTIPLY_REGEX,
    DO_REGEX,
    DONT_REGEX,
)

private val INSTRUCTION_REGEX = INSTRUCTION_REGEXES
    .joinToString(separator = "|")
    .toRegex()

fun String.toInstructions(): Sequence<Instruction> {
    return INSTRUCTION_REGEX
        .findAll(this)
        .map(MatchResult::toInstruction)
}

private fun MatchResult.toInstruction(): Instruction {
    return when (value) {
        "do()" -> DoInstruction
        "don't()" -> DontInstruction
        else -> toMultiplyInstruction()
    }
}

private fun MatchResult.toMultiplyInstruction(): MultiplyInstruction {
    val (left, right) = destructured

    return MultiplyInstruction(
        left = left.toInt(),
        right = right.toInt(),
    )
}

sealed interface Instruction

data class MultiplyInstruction(
    val left: Int,
    val right: Int,
) : Instruction

sealed interface ConditionalInstruction : Instruction

data object DoInstruction : ConditionalInstruction

data object DontInstruction : ConditionalInstruction
