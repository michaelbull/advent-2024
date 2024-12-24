package com.github.michaelbull.advent2024.day24

val WIRE_ID_REGEX = """[a-z0-9]{3}""".toRegex()
private val REGEX = """($WIRE_ID_REGEX): ([01])""".toRegex()

fun String.toWire(): Wire {
    val match = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match '$REGEX"
    }

    val (id, value) = match.destructured

    require(value == "0" || value == "1") {
        "value must be either 0 or 1"
    }

    return Wire(
        first = id,
        second = value == "1",
    )
}

typealias Wire = Pair<String, Boolean>
