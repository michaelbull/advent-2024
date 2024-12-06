package com.github.michaelbull.advent2024.math

import java.util.BitSet

class Vector2BooleanMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Boolean = { DEFAULT_VALUE },
) : Iterable<Pair<Vector2, Boolean>> {

    val xRange = 0..<width
    val yRange = 0..<height

    val first = Vector2(xRange.first, yRange.first)
    val last = Vector2(xRange.last, yRange.last)

    private val values = BitSet(width * height)

    init {
        for (x in xRange) {
            for (y in yRange) {
                val position = Vector2(x, y)
                set(position, init(position))
            }
        }
    }

    operator fun set(position: Vector2, value: Boolean) {
        values[indexOf(position)] = value
    }

    operator fun set(x: Int, y: Int, value: Int) {
        values[indexOf(x, y)] = value
    }

    operator fun get(x: Int, y: Int): Boolean {
        return values[indexOf(x, y)]
    }

    operator fun get(position: Vector2): Boolean {
        return values[indexOf(position)]
    }

    fun getOrDefault(x: Int, y: Int, defaultValue: Boolean): Boolean {
        return if (x in xRange && y in yRange) {
            values[indexOf(x, y)]
        } else {
            defaultValue
        }
    }

    fun getOrDefault(position: Vector2, defaultValue: Boolean): Boolean {
        return if (position in this) {
            values[indexOf(position)]
        } else {
            defaultValue
        }
    }

    fun getOrElse(x: Int, y: Int, defaultValue: () -> Boolean): Boolean {
        return if (x in xRange && y in yRange) {
            values[indexOf(x, y)]
        } else {
            defaultValue()
        }
    }

    fun getOrElse(position: Vector2, defaultValue: () -> Boolean): Boolean {
        return if (position in this) {
            values[indexOf(position)]
        } else {
            defaultValue()
        }
    }

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    fun copy(
        width: Int = this.width,
        height: Int = this.height,
        defaultValue: Boolean = DEFAULT_VALUE,
    ): Vector2BooleanMap {
        return Vector2BooleanMap(width, height) { (x, y) ->
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

    override fun iterator(): Iterator<Pair<Vector2, Boolean>> {
        return iterator {
            for (x in xRange) {
                for (y in yRange) {
                    val index = indexOf(x, y)
                    val position = Vector2(x, y)
                    yield(position to values[index])
                }
            }
        }
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
        private const val DEFAULT_VALUE = false
    }
}
