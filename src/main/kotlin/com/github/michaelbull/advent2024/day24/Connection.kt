package com.github.michaelbull.advent2024.day24

private val REGEX = """($WIRE_ID_REGEX) (AND|OR|XOR) ($WIRE_ID_REGEX) -> ($WIRE_ID_REGEX)""".toRegex()

fun String.toConnection(): Connection {
    val match = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (from, operand, to, output) = match.destructured

    return Connection(
        from = from,
        operand = operand.toOperand(),
        to = to,
        output = output,
    )
}

data class Connection(
    val from: String,
    val operand: Operand,
    val to: String,
    val output: String,
)
