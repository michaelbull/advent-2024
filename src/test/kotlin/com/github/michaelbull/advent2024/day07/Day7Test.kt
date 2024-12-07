package com.github.michaelbull.advent2024.day07

import com.github.michaelbull.advent2024.day07.Day7.part1
import com.github.michaelbull.advent2024.day07.Day7.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun `example 1`() {
        val input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """

        assertEquals(3749, Day7.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """

        assertEquals(11387, Day7.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(6083020304036, Day7.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(59002246504791, Day7.solve(::part2))
    }
}
