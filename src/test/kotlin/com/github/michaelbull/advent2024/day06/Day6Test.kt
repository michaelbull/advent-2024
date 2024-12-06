package com.github.michaelbull.advent2024.day06

import com.github.michaelbull.advent2024.day06.Day6.part1
import com.github.michaelbull.advent2024.day06.Day6.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `example 1`() {
        val input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """

        assertEquals(41, Day6.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """

        assertEquals(6, Day6.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(5551, Day6.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1939, Day6.solve(::part2))
    }
}
