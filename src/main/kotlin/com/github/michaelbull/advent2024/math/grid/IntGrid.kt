package com.github.michaelbull.advent2024.math.grid

import com.github.michaelbull.advent2024.math.Vector2

fun Sequence<String>.toIntGrid(): IntGrid {
    return toList().toIntGrid()
}

fun List<String>.toIntGrid(): IntGrid {
    val width = first().length
    val height = size

    return IntGrid(width, height) { (x, y) ->
        val line = this[y]
        val char = line[x]
        char.digitToInt()
    }
}

class IntGrid(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Int = { DEFAULT_VALUE },
) : Iterable<Pair<Vector2, Int>> {

    val xRange = 0..<width
    val yRange = 0..<height

    val first = Vector2(xRange.first, yRange.first)
    val last = Vector2(xRange.last, yRange.last)

    init {
        require(width > 0)
        require(height > 0)
    }

    private val values = IntArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    operator fun set(position: Vector2, value: Int) {
        values[indexOf(position)] = value
    }

    operator fun set(x: Int, y: Int, value: Int) {
        values[indexOf(x, y)] = value
    }

    operator fun get(position: Vector2): Int {
        return values[indexOf(position)]
    }

    operator fun get(x: Int, y: Int): Int {
        return values[indexOf(x, y)]
    }

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    fun getOrDefault(x: Int, y: Int, defaultValue: Int): Int {
        return if (x in xRange && y in yRange) {
            get(x, y)
        } else {
            defaultValue
        }
    }

    fun getOrDefault(position: Vector2, defaultValue: Int): Int {
        return if (position in this) {
            get(position)
        } else {
            defaultValue
        }
    }

    inline fun getOrElse(x: Int, y: Int, defaultValue: () -> Int): Int {
        return if (x in xRange && y in yRange) {
            get(x, y)
        } else {
            defaultValue()
        }
    }

    inline fun getOrElse(position: Vector2, defaultValue: () -> Int): Int {
        return if (position in this) {
            get(position)
        } else {
            defaultValue()
        }
    }

    fun copy(
        width: Int = this.width,
        height: Int = this.height,
        defaultValue: Int = DEFAULT_VALUE,
    ): IntGrid {
        return IntGrid(width, height) { (x, y) ->
            if (x in xRange && y in yRange) {
                this[x, y]
            } else {
                defaultValue
            }
        }
    }

    fun positionsIterator() = iterator {
        forEachPosition { x, y ->
            yield(Vector2(x, y))
        }
    }

    fun positions(): Iterable<Vector2> {
        return Iterable(::positionsIterator)
    }

    fun valuesIterator(): IntIterator {
        return ValueIterator()
    }

    fun values(): Iterable<Int> {
        return Iterable(::valuesIterator)
    }

    override fun iterator(): Iterator<Pair<Vector2, Int>> {
        return iterator {
            forEachPosition { x, y ->
                val position = Vector2(x, y)
                val value = get(position)
                yield(position to value)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is IntGrid) {
            values.contentEquals(other.values)
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return values.contentHashCode()
    }

    private inline fun forEachPosition(action: (Int, Int) -> Unit) {
        for (x in xRange) {
            for (y in yRange) {
                action(x, y)
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

    private inner class ValueIterator : IntIterator() {
        private var hasNext = true
        private var x = 0
        private var y = 0

        override fun hasNext(): Boolean {
            return hasNext
        }

        override fun nextInt(): Int {
            val value = get(x, y)

            if (x == xRange.last && y == yRange.last) {
                if (!hasNext) throw NoSuchElementException()
                hasNext = false
            } else {
                step()
            }

            return value
        }

        private fun step() {
            when {
                x != xRange.last -> {
                    x++
                }

                y != yRange.last -> {
                    y++
                    x = 0
                }

                else -> throw NoSuchElementException()
            }
        }
    }

    private companion object {
        private const val DEFAULT_VALUE = 0
    }
}
