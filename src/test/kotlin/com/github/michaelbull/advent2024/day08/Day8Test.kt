package com.github.michaelbull.advent2024.day08

import com.github.michaelbull.advent2024.day08.Day8.part1
import com.github.michaelbull.advent2024.day08.Day8.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun `example 1`() {
        val input = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """

        assertEquals(14, Day8.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """

        assertEquals(34, Day8.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(259, Day8.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(927, Day8.solve(::part2))
    }
}
