package com.github.michaelbull.advent2024.day22

import com.github.michaelbull.advent2024.math.range.symmetricRange
import kotlin.math.pow

fun Sequence<String>.toMarket(): Market {
    return Market(map(String::toLong).toList())
}

private typealias Evolution = (Long) -> Long

class Market(
    private val secretNumbers: List<Long>,
) {

    private val evolutions = listOf(
        ::mul64,
        ::div32,
        ::mul2048,
    )

    fun sumSecretNumbers(number: Int): Long {
        return secretNumbers.sumOf { initial ->
            evolutionSequence(initial, evolutions)
                .take(number + 1)
                .last()
        }
    }

    fun maxBananas(number: Int): Long {
        val indices = IntArray(SEQUENCES)
        val bananas = LongArray(SEQUENCES)

        for ((index, secretNumber) in secretNumbers.withIndex()) {
            for (priceChange in priceChanges(number, secretNumber)) {
                val (a, b, c, d, e) = priceChange
                val hash = hashPriceChange(b - a, c - b, d - c, e - d)

                if (indices[hash] != index + 1) {
                    indices[hash] = index + 1
                    bananas[hash] = bananas[hash] + priceChange.last()
                }
            }
        }

        return bananas.max()
    }

    private fun evolutionSequence(secretNumber: Long, evolutions: Iterable<Evolution>): Sequence<Long> {
        return generateSequence(secretNumber) { secretNumber ->
            evolve(secretNumber, evolutions)
        }
    }

    private fun evolve(secretNumber: Long, evolutions: Iterable<Evolution>): Long {
        return evolutions.fold(secretNumber) { secretNumber, evolve ->
            evolve(secretNumber).mix(secretNumber).prune()
        }
    }

    private fun mul2048(result: Long): Long {
        return result shl 11
    }

    private fun div32(result: Long): Long {
        return result shr 5
    }

    private fun mul64(result: Long): Long {
        return result shl 6
    }

    private fun Long.prune(): Long {
        return this and PRUNE_MASK
    }

    private fun Long.mix(number: Long): Long {
        return number xor this
    }

    private fun priceChanges(number: Int, secretNumber: Long): Sequence<List<Long>> {
        return evolutionSequence(secretNumber, evolutions)
            .take(number + 1)
            .map { it % PRICES }
            .windowed(CHANGES + 1)
    }

    private fun hashPriceChange(a: Long, b: Long, c: Long, d: Long): Int {
        var result = 0L
        result += (a + PRICE_RANGE.last) * PRICE_RANGE_COUNT * PRICE_RANGE_COUNT * PRICE_RANGE_COUNT
        result += (b + PRICE_RANGE.last) * PRICE_RANGE_COUNT * PRICE_RANGE_COUNT
        result += (c + PRICE_RANGE.last) * PRICE_RANGE_COUNT
        result += (d + PRICE_RANGE.last)
        return result.toInt()
    }

    private companion object {
        private const val PRUNE_MASK = 16777216L - 1

        private const val CHANGES = 4
        private const val PRICES = 10

        private val PRICE_RANGE = PRICES.symmetricRange()
        private val PRICE_RANGE_COUNT = PRICE_RANGE.count()

        private val SEQUENCES = PRICE_RANGE.count()
            .toDouble()
            .pow(CHANGES)
            .toInt()
    }
}
