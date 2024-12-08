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
    val width: Int,
    val height: Int,
    init: (Vector2) -> Char = { DEFAULT_VALUE },
) : Iterable<Pair<Vector2, Char>> {

    val xRange = 0..<width
    val yRange = 0..<height

    val first = Vector2(xRange.first, yRange.first)
    val last = Vector2(xRange.last, yRange.last)

    private val values = CharArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    operator fun set(position: Vector2, value: Char) {
        values[indexOf(position)] = value
    }

    operator fun set(x: Int, y: Int, value: Char) {
        values[indexOf(x, y)] = value
    }

    operator fun get(position: Vector2): Char {
        return values[indexOf(position)]
    }

    operator fun get(x: Int, y: Int): Char {
        return values[indexOf(x, y)]
    }

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    fun getOrDefault(x: Int, y: Int, defaultValue: Char): Char {
        return if (x in xRange && y in yRange) {
            get(x, y)
        } else {
            defaultValue
        }
    }

    fun getOrDefault(position: Vector2, defaultValue: Char): Char {
        return if (position in this) {
            get(position)
        } else {
            defaultValue
        }
    }

    inline fun getOrElse(x: Int, y: Int, defaultValue: () -> Char): Char {
        return if (x in xRange && y in yRange) {
            get(x, y)
        } else {
            defaultValue()
        }
    }

    inline fun getOrElse(position: Vector2, defaultValue: () -> Char): Char {
        return if (position in this) {
            get(position)
        } else {
            defaultValue()
        }
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

    fun positionsIterator() = iterator {
        for (x in xRange) {
            for (y in yRange) {
                yield(Vector2(x, y))
            }
        }
    }

    fun positions(): Iterable<Vector2> {
        return Iterable(::positionsIterator)
    }

    fun valuesIterator() = iterator {
        for (position in positionsIterator()) {
            yield(get(position))
        }
    }

    fun values(): Iterable<Char> {
        return Iterable(::valuesIterator)
    }

    override fun iterator(): Iterator<Pair<Vector2, Char>> {
        return iterator {
            for (x in xRange) {
                for (y in yRange) {
                    val position = Vector2(x, y)
                    val value = get(position)
                    yield(position to value)
                }
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
