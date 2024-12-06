package com.github.michaelbull.advent2024.day06

import com.github.michaelbull.advent2024.math.Vector2

sealed interface Path {
    val positions: Set<Vector2>
    val steps: Set<Step>
}

data object EmptyPath : Path {
    override val positions: Set<Vector2> = emptySet()
    override val steps: Set<Step> = emptySet()
}

data class OpenPath(
    override val positions: Set<Vector2>,
    override val steps: Set<Step>,
) : Path

data class ClosedPath(
    override val positions: Set<Vector2>,
    override val steps: Set<Step>,
) : Path
