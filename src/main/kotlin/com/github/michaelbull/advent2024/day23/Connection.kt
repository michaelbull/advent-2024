package com.github.michaelbull.advent2024.day23

private val REGEX = """([a-z]{2})-([a-z]{2})""".toRegex()

fun String.toConnection(): Connection {
    val match = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (from, to) = match.destructured

    return Connection(from, to)
}

data class Connection(
    val from: String,
    val to: String,
)
