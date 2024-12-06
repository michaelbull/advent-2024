package com.github.michaelbull.advent2024.math.grid

import com.github.michaelbull.advent2024.math.Vector2

fun Sequence<String>.toCharGrid(): CharGrid {
    return toList().toCharGrid()
}

fun List<String>.toCharGrid(): CharGrid {
    val width = first().length
    val height = size

    return CharGrid(width, height) { (x, y) ->
        val line = this[y]
        val char = line[x]
        char
    }
}

class CharGrid(
    override val width: Int,
    override val height: Int,
    init: (Vector2) -> Char = { DEFAULT_VALUE },
) : Grid<Char> {

    override val xRange = 0..<width
    override val yRange = 0..<height

    override val first = Vector2(xRange.first, yRange.first)
    override val last = Vector2(xRange.last, yRange.last)

    private val values = CharArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    override operator fun set(position: Vector2, value: Char) {
        values[indexOf(position)] = value
    }

    override operator fun set(x: Int, y: Int, value: Char) {
        values[indexOf(x, y)] = value
    }

    override operator fun get(position: Vector2): Char {
        return values[indexOf(position)]
    }

    override operator fun get(x: Int, y: Int): Char {
        return values[indexOf(x, y)]
    }

    fun copy(
        width: Int = this.width,
        height: Int = this.height,
        defaultValue: Char = DEFAULT_VALUE,
    ): CharGrid {
        return CharGrid(width, height) { (x, y) ->
            if (x in xRange && y in yRange) {
                this[x, y]
            } else {
                defaultValue
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is CharGrid) {
            values.contentEquals(other.values)
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return values.contentHashCode()
    }

    private fun indexOf(position: Vector2): Int {
        return indexOf(position.x, position.y)
    }

    private fun indexOf(x: Int, y: Int): Int {
        requireInBounds(x, y)
        return (y * width) + x
    }

    private fun requireInBounds(x: Int, y: Int) {
        require(x in xRange) { "x must be in $xRange, but was $x" }
        require(y in yRange) { "y must be in $yRange, but was $y" }
    }

    private companion object {
        private const val DEFAULT_VALUE = Char.MIN_VALUE
    }
}
