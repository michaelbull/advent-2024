package com.github.michaelbull.advent2024.day11

import com.github.michaelbull.advent2024.day11.Day11.part1
import com.github.michaelbull.advent2024.day11.Day11.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `example 1`() {
        val input = """
            125 17
        """

        assertEquals(55312, Day11.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(185894, Day11.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(221632504974231, Day11.solve(::part2))
    }
}
