package com.github.michaelbull.advent2024.math

import com.github.michaelbull.advent2024.math.range.Vector3Range
import com.github.michaelbull.advent2024.math.range.rem
import kotlin.math.abs
import kotlin.math.sign

fun Triple<Int, Int, Int>.toVector3(): Vector3 {
    return Vector3(this)
}

data class Vector3(
    val x: Int = 0,
    val y: Int = 0,
    val z: Int = 0,
) : Comparable<Vector3> {

    constructor(triple: Triple<Int, Int, Int>) : this(
        x = triple.first,
        y = triple.second,
        z = triple.third,
    )

    operator fun unaryPlus(): Vector3 {
        return this
    }

    operator fun plus(amount: Int): Vector3 {
        return copy(
            x = this.x + amount,
            y = this.y + amount,
            z = this.z + amount,
        )
    }

    operator fun plus(other: Vector3): Vector3 {
        return copy(
            x = this.x + other.x,
            y = this.y + other.y,
            z = this.z + other.z,
        )
    }

    operator fun unaryMinus(): Vector3 {
        return copy(
            x = -this.x,
            y = -this.y,
            z = -this.z,
        )
    }

    operator fun minus(amount: Int): Vector3 {
        return copy(
            x = this.x - amount,
            y = this.y - amount,
            z = this.z - amount,
        )
    }

    operator fun minus(other: Vector3): Vector3 {
        return copy(
            x = this.x - other.x,
            y = this.y - other.y,
            z = this.z - other.z,
        )
    }

    operator fun times(amount: Int): Vector3 {
        return copy(
            x = this.x * amount,
            y = this.y * amount,
            z = this.z * amount,
        )
    }

    operator fun times(other: Vector3): Vector3 {
        return copy(
            x = this.x * other.x,
            y = this.y * other.y,
            z = this.z * other.z,
        )
    }

    operator fun div(amount: Int): Vector3 {
        return copy(
            x = this.x / amount,
            y = this.y / amount,
            z = this.z / amount,
        )
    }

    operator fun div(other: Vector3): Vector3 {
        return copy(
            x = this.x / other.x,
            y = this.y / other.y,
            z = this.z / other.z,
        )
    }

    operator fun rem(amount: Int): Vector3 {
        return copy(
            x = this.x % amount,
            y = this.y % amount,
            z = this.z % amount,
        )
    }

    operator fun rem(other: Vector3): Vector3 {
        return copy(
            x = this.x % other.x,
            y = this.y % other.y,
            z = this.z % other.z,
        )
    }

    operator fun rem(range: Vector3Range): Vector3 {
        return copy(
            x = this.x % range.xRange,
            y = this.y % range.yRange,
            z = this.z % range.zRange,
        )
    }

    infix fun cross(other: Vector3): Vector3 {
        return copy(
            x = (this.y * other.z) - (this.z * other.y),
            y = (this.z * other.x) - (this.x * other.z),
            z = (this.x * other.y) - (this.y * other.x),
        )
    }

    infix fun dot(other: Vector3): Long {
        val a = this.x.toLong() * other.x.toLong()
        val b = this.y.toLong() * other.y.toLong()
        val c = this.z.toLong() * other.z.toLong()
        return a + b + c
    }

    fun abs(): Vector3 {
        return copy(
            x = abs(this.x),
            y = abs(this.y),
            z = abs(this.z),
        )
    }

    fun sign(): Vector3 {
        return copy(
            x = this.x.sign,
            y = this.y.sign,
            z = this.z.sign,
        )
    }

    fun coerceAtLeast(minimumValue: Vector3): Vector3 {
        return copy(
            x = this.x.coerceAtLeast(minimumValue.x),
            y = this.y.coerceAtLeast(minimumValue.y),
            z = this.z.coerceAtLeast(minimumValue.z),
        )
    }

    fun coerceAtMost(maximumValue: Vector3): Vector3 {
        return copy(
            x = this.x.coerceAtMost(maximumValue.x),
            y = this.y.coerceAtMost(maximumValue.y),
            z = this.z.coerceAtMost(maximumValue.z),
        )
    }

    operator fun rangeTo(other: Vector3): Vector3Range {
        return Vector3Range(this, other)
    }

    override fun compareTo(other: Vector3): Int {
        return when {
            x < other.x -> -1
            x > other.x -> +1
            y < other.y -> -1
            y > other.y -> +1
            z < other.z -> -1
            z > other.z -> +1
            else -> 0
        }
    }

    override fun toString(): String {
        return "[$x, $y, $z]"
    }

    companion object {
        val DIMENSIONS = setOf(
            Vector3::x,
            Vector3::y,
            Vector3::z,
        )

        val MIN_VALUE = Vector3(
            x = Int.MIN_VALUE,
            y = Int.MIN_VALUE,
            z = Int.MIN_VALUE,
        )

        val MAX_VALUE = Vector3(
            x = Int.MAX_VALUE,
            y = Int.MAX_VALUE,
            z = Int.MAX_VALUE,
        )

        val ZERO = Vector3(
            x = 0,
            y = 0,
            z = 0,
        )

        val UP = Vector3(
            x = +1,
            y = +1,
            z = +1,
        )

        val DOWN = Vector3(
            x = -1,
            y = -1,
            z = -1,
        )
    }
}
