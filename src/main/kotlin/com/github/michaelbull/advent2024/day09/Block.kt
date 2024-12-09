package com.github.michaelbull.advent2024.day09

sealed interface Block {
    val id: Long
}

data class File(
    override val id: Long,
    var startPosition: Int,
    val size: Int,
) : Block {

    val endPosition: Int
        get() = startPosition + size

    val positionRange: IntRange
        get() = startPosition..<endPosition
}

data class Space(
    var startPosition: Int,
    var size: Int,
) : Block {
    override val id = 0L
}
