package com.github.michaelbull.advent2024.day01

import com.github.michaelbull.advent2024.day01.Day1.part1
import com.github.michaelbull.advent2024.day01.Day1.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `example 1`() {
        val input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """

        assertEquals(11, Day1.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """

        assertEquals(31, Day1.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2375403, Day1.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(23082277, Day1.solve(::part2))
    }
}
