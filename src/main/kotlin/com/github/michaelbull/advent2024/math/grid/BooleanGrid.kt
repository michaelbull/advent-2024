package com.github.michaelbull.advent2024.math.grid

import com.github.michaelbull.advent2024.math.Vector2
import java.util.BitSet

class BooleanGrid(
    override val width: Int,
    override val height: Int,
    init: (Vector2) -> Boolean = { DEFAULT_VALUE },
) : Grid<Boolean> {

    override val xRange = 0..<width
    override val yRange = 0..<height

    override val first = Vector2(xRange.first, yRange.first)
    override val last = Vector2(xRange.last, yRange.last)

    private val values = BitSet(width * height)

    init {
        for (x in xRange) {
            for (y in yRange) {
                val position = Vector2(x, y)
                set(position, init(position))
            }
        }
    }

    override operator fun set(position: Vector2, value: Boolean) {
        values[indexOf(position)] = value
    }

    override operator fun set(x: Int, y: Int, value: Boolean) {
        values[indexOf(x, y)] = value
    }

    override operator fun get(position: Vector2): Boolean {
        return values[indexOf(position)]
    }

    override operator fun get(x: Int, y: Int): Boolean {
        return values[indexOf(x, y)]
    }

    fun copy(
        width: Int = this.width,
        height: Int = this.height,
        defaultValue: Boolean = DEFAULT_VALUE,
    ): BooleanGrid {
        return BooleanGrid(width, height) { (x, y) ->
            if (x in xRange && y in yRange) {
                this[x, y]
            } else {
                defaultValue
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
