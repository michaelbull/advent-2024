package com.github.michaelbull.advent2024.day10

import com.github.michaelbull.advent2024.day10.Day10
import com.github.michaelbull.advent2024.day10.Day10.part1
import com.github.michaelbull.advent2024.day10.Day10.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun `example 1`() {
        val input = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """

        assertEquals(36, Day10.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """

        assertEquals(81, Day10.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(719, Day10.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1530, Day10.solve(::part2))
    }
}
