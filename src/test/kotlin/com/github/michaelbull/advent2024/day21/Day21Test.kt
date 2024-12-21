package com.github.michaelbull.advent2024.day21

import com.github.michaelbull.advent2024.day21.Day21.part1
import com.github.michaelbull.advent2024.day21.Day21.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    @Test
    fun `example 1`() {
        val input = """
            029A
            980A
            179A
            456A
            379A
        """

        assertEquals(126384, Day21.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(197560, Day21.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(242337182910752, Day21.solve(::part2))
    }
}
