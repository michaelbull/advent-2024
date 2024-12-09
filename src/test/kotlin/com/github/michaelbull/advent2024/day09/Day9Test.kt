package com.github.michaelbull.advent2024.day09

import com.github.michaelbull.advent2024.day09.Day9.part1
import com.github.michaelbull.advent2024.day09.Day9.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun `example 1`() {
        val input = """
            2333133121414131402
        """

        assertEquals(1928, Day9.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            2333133121414131402
        """

        assertEquals(2858, Day9.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(6337921897505, Day9.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(6362722604045, Day9.solve(::part2))
    }
}
