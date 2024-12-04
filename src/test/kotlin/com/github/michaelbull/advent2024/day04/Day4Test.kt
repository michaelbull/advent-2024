package com.github.michaelbull.advent2024.day04

import com.github.michaelbull.advent2024.day04.Day4.part1
import com.github.michaelbull.advent2024.day04.Day4.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Test {

    @Test
    fun `example 1`() {
        val input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """

        assertEquals(18, Day4.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """

        assertEquals(9, Day4.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2517, Day4.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1960, Day4.solve(::part2))
    }
}
