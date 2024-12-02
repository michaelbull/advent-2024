package com.github.michaelbull.advent2024.day02

import com.github.michaelbull.advent2024.day02.Day2.part1
import com.github.michaelbull.advent2024.day02.Day2.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun `example 1`() {
        val input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """

        assertEquals(2, Day2.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """

        assertEquals(4, Day2.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(306, Day2.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(366, Day2.solve(::part2))
    }
}
