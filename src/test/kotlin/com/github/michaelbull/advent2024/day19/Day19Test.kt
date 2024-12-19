package com.github.michaelbull.advent2024.day19

import com.github.michaelbull.advent2024.day19.Day19.part1
import com.github.michaelbull.advent2024.day19.Day19.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {

    @Test
    fun `example 1`() {
        val input = """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
        """

        assertEquals(6, Day19.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
        """

        assertEquals(16, Day19.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(363, Day19.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(642535800868438, Day19.solve(::part2))
    }
}
