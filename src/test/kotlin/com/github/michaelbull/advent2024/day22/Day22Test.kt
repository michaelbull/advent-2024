package com.github.michaelbull.advent2024.day22

import com.github.michaelbull.advent2024.day22.Day22.part1
import com.github.michaelbull.advent2024.day22.Day22.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun `example 1`() {
        val input = """
            1
            10
            100
            2024
        """

        assertEquals(37327623, Day22.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            1
            2
            3
            2024
        """

        assertEquals(23, Day22.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(20071921341, Day22.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(2242, Day22.solve(::part2))
    }
}
