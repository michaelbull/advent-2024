package com.github.michaelbull.advent2024.day03

import com.github.michaelbull.advent2024.day03.Day3.part1
import com.github.michaelbull.advent2024.day03.Day3.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun `example 1`() {
        val input = """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
        """

        assertEquals(161, Day3.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
        """

        assertEquals(48, Day3.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(178886550, Day3.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(87163705, Day3.solve(::part2))
    }
}
