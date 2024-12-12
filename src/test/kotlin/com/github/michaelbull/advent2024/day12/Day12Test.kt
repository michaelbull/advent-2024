package com.github.michaelbull.advent2024.day12

import com.github.michaelbull.advent2024.day12.Day12.part1
import com.github.michaelbull.advent2024.day12.Day12.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun `example 1`() {
        val input = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """

        assertEquals(1930, Day12.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """

        assertEquals(1206, Day12.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1494342, Day12.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(893676, Day12.solve(::part2))
    }
}
