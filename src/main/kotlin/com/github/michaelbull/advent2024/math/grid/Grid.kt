package com.github.michaelbull.advent2024.math.grid

import com.github.michaelbull.advent2024.math.Vector2

interface Grid<T : Any> : Iterable<Pair<Vector2, T>> {
    val width: Int
    val height: Int

    val xRange: IntRange
    val yRange: IntRange

    val first: Vector2
    val last: Vector2

    operator fun set(position: Vector2, value: T)
    operator fun set(x: Int, y: Int, value: T)

    operator fun get(position: Vector2): T
    operator fun get(x: Int, y: Int): T

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    fun getOrDefault(x: Int, y: Int, defaultValue: T): T {
        return if (x in xRange && y in yRange) {
            get(x, y)
        } else {
            defaultValue
        }
    }

    fun getOrDefault(position: Vector2, defaultValue: T): T {
        return if (position in this) {
            get(position)
        } else {
            defaultValue
        }
    }

    fun getOrElse(x: Int, y: Int, defaultValue: () -> T): T {
        return if (x in xRange && y in yRange) {
            get(x, y)
        } else {
            defaultValue()
        }
    }

    fun getOrElse(position: Vector2, defaultValue: () -> T): T {
        return if (position in this) {
            get(position)
        } else {
            defaultValue()
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

    override fun iterator(): Iterator<Pair<Vector2, T>> {
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
}
