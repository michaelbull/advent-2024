package com.github.michaelbull.advent2024.day06

import com.github.michaelbull.advent2024.math.Vector2

sealed interface Path {
    val positions: Set<Vector2>
}

data class TerminalPath(
    override val positions: Set<Vector2>,
) : Path

data class LoopingPath(
    override val positions: Set<Vector2>,
) : Path
